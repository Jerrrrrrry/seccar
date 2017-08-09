package com.lhcy.sync.web.action;

import com.hongchen.kis.db.ConfigDataInitializer;
import com.hongchen.sync.reader.FileProcessor;
import com.hongchen.sync.util.Constants;
import com.lhcy.core.bo.SysConstant;
import com.lhcy.core.util.ContextUtils;
import com.lhcy.core.util.JsonUtils;
import com.lhcy.sync.domain.dto.UploadedDataFileDto;
import com.lhcy.sync.web.form.UploadFileForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class VoucherAction extends DispatchAction {

    private Logger logger = Logger.getLogger(VoucherAction.class);

	public ActionForward uploadFile(ActionMapping mapping, ActionForm frm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FileOutputStream outer = null;
		FormFile uploadFile = null;
		try {
			// 检查登录
			if (ContextUtils.getCurrentUserID(request) == null) {
				throw new Exception( SysConstant.M_NO_LOGIN);
			}

			uploadFile = (FormFile) frm.getMultipartRequestHandler().getFileElements().get("file1"); 
			int fileSize = uploadFile.getFileSize();
			FileProcessor processor = new FileProcessor();
			File newFile = new File(Constants.PROCESSING_FOLDER_PATH + processor.toProcessingFileName(uploadFile.getFileName()));
			outer = new FileOutputStream(newFile);
			byte[] buffer = uploadFile.getFileData();
			outer.write(buffer);
			outer.flush();
			uploadFile.destroy();
			initRefData();
			String uploadMsg = processor.process(newFile, ContextUtils.getCurrentUserID(request), ContextUtils.getCurrentKisUserID(request));
			outer.close();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{\"size\":\""+GetFileSize(fileSize)+ "\", \"uploadMsg\":\"" +uploadMsg+"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			request.getSession().setAttribute("ex", e);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("{\"size\":\"DO_NOT_LOGIN\", \"uploadMsg\":\"当前用户未登陆系统\"}");
		
		} finally {
			if (outer != null)
			{
				try{outer.close();}catch(Exception e){}
			}
			if (uploadFile != null)
			{
				try{uploadFile.destroy();}catch(Exception e){}
			}
		}

		return null;
	}
	private void initRefData()
	{
		long begin = System.currentTimeMillis();
		ConfigDataInitializer inil= new ConfigDataInitializer();
		System.out.print("开始初始化引用数据");
		inil.initAll();
		long end = System.currentTimeMillis();
		System.out.print("初始化引用数据完成,用时:" + (end-begin) + "ms");
	}
	public String GetFileSize(long fileS) {
		String size = "";
		//long fileS = fileSize + 0L;
		DecimalFormat df = new DecimalFormat("#.00");
		if (fileS < 1024) {
			size = df.format((double) fileS) + "BT";
		} else if (fileS < 1048576) {
			size = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			size = df.format((double) fileS / 1048576) + "MB";
		} else {
			size = df.format((double) fileS / 1073741824) + "GB";
		}
		return size;
	}
	public ActionForward list(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		        try {
		            // 检查登录
		            if (ContextUtils.getCurrentUserID(request) == null) {
		                throw new Exception(SysConstant.M_NO_LOGIN);
		            }
		
		            UploadFileForm form = (UploadFileForm)frm;
		            File newFile = new File(Constants.PROCESSING_FOLDER_PATH);
		            int totalFiles = 0;
		            File[] files = null;
					if(newFile.isDirectory()){
						files = newFile.listFiles();
						if (files != null) {
							totalFiles = files.length;
							Arrays.sort(files, new Comparator<File>() {
								public int compare(File f1, File f2) {
									long diff = f1.lastModified() - f2.lastModified();
									if (diff > 0)
										return -1;
									else if (diff == 0)
										return 0;
									else
										return 1;
								}
		
								public boolean equals(Object obj) {
									return true;
								}
		
							});
		
						}
					}
		            int pageSize = form.getRows();
		            int pageNow = form.getPage();
		            int count = totalFiles;
		            int rowBegin = (pageNow - 1) * pageSize;
		            int rowEnd = rowBegin + pageSize;
		            if(rowBegin > 0) rowBegin++;
		            
		            List<UploadedDataFileDto> list  = new ArrayList<UploadedDataFileDto>();
		            if (files != null){
		            	if (rowBegin < files.length){
		            		for (int i = rowBegin; i < rowEnd; i++)
		            		{
		            			if (i==files.length)
		            			{
		            				break;
		            			}
		            			UploadedDataFileDto dto = new UploadedDataFileDto();
		            			File file = files[i];
		            			String fileName = file.getName();
		            			String[] names = fileName.split("_");
		            			dto.setId(names[names.length-1]);
		            			dto.setUploadDate(toDate(names[names.length-2]));
		            			dto.setFileName(fileName.substring(0,fileName.length()-(names[names.length-1]+"_"+names[names.length-2]).length()-1)+fileName.substring(fileName.lastIndexOf(".")));
		            			dto.setSize(this.GetFileSize(file.length()));
		            			list.add(dto);
		            		}
		            	}
		            }
		          
		
		            JsonUtils.printFromList(response, list, count);
		        }catch(Exception e){
		            e.printStackTrace();
		            logger.error(e.getMessage());
		            request.getSession().setAttribute("ex", e);
		        }
		
		        return null;
		    }
	private String toDate(String dateTime)
	{
		String year = dateTime.substring(0, 4);
		String month = dateTime.substring(4, 6);
		String day = dateTime.substring(6, 8);
		String hour = dateTime.substring(8, 10);
		String min = dateTime.substring(10, 12);
		String second = dateTime.substring(12, 14);
		return year+"-"+month+"-"+day+" "+ hour + ":"+min+":"+second;
	}
	
//    /***********************************************/
//    // 取得列表
//    /***********************************************/
//    public ActionForward list(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        try {
//            // 检查登录
//            if (ContextUtils.getCurrentUserID(request) == null) {
//                throw new BizException(BizStatus.nologin, SysConstant.M_NO_LOGIN);
//            }
//
//            VoucherForm form = (VoucherForm)frm;
//            SettltSumService tsv = new SettltSumService();
//
//            int pageSize = form.getRows();
//            int pageNow = form.getPage();
//            int count = tsv.count(form);
//            int rowBegin = (pageNow - 1) * pageSize;
//            int rowEnd = rowBegin + pageSize;
//            if(rowBegin > 0) rowBegin++;
//
//            List<VoucherTmpListDto> list = null;
//            list = tsv.list(rowBegin, rowEnd, form);
//
//            JsonUtils.printFromList(response, list, count);
//        }catch(Exception e){
//            e.printStackTrace();
//            logger.error(e.getMessage());
//            request.getSession().setAttribute("ex", e);
//        }
//
//        return null;
//    }
//
//    /***********************************************/
//    // 同步
//    /***********************************************/
//    public ActionForward sync(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        try {
//            // 检查登录
//            if (ContextUtils.getCurrentUserID(request) == null) {
//                throw new BizException(BizStatus.nologin, SysConstant.M_NO_LOGIN);
//            }
//
//            VoucherForm form = (VoucherForm)frm;
//
//            if (form.getId().length() == 0){
//                JsonUtils.printActionResultOK(response);
//                return null;
//            }
//
//            String[] pk = form.getId().split(",");
//            SettltSumService tsv = new SettltSumService();
//            List<VoucherTempDto> list = tsv.voucherList(pk);
//
//            String msg = "";
//            VoucherSyncService sv = new VoucherSyncService();
//            msg = sv.sync(list, ContextUtils.getCurrentUserID(request), ContextUtils.getCurrentKisUserID(request));
//
//            if (msg.length() > 0){
//                throw new BizException(BizStatus.warning ,msg);
//            }else{
//                JsonUtils.printActionResultOK(response);
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//            logger.error(e.getMessage());
//            JsonUtils.printActionResultFromException(response, e);
//        }
//
//        return null;
//    }

    /***********************************************/
    // 跳转
    /***********************************************/
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 检查登录
        if (ContextUtils.getCurrentUserID(request) == null) {
            ActionForward forward = new ActionForward("/NoLoginAction.do");
            forward.setRedirect(true);
            return forward;
        }

        return mapping.findForward("show");
    }
}
