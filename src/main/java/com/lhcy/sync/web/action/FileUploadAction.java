package com.lhcy.sync.web.action;

import com.hongchen.sync.util.Constants;
import com.lhcy.core.bo.SysConstant;
import com.lhcy.core.bo.UuidCreator;
import com.lhcy.core.util.ContextUtils;
import com.lhcy.core.util.JsonUtils;
import com.lhcy.sync.domain.dto.AccountDto;
import com.lhcy.sync.domain.pojo.Account;
import com.lhcy.sync.service.AccountService;
import com.lhcy.sync.web.form.AccountForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class FileUploadAction extends DispatchAction {

    private Logger logger = Logger.getLogger(FileUploadAction.class);

    private File[] file;              //鏂囦欢  
    private String[] fileFileName;    //鏂囦欢鍚�  
    private String[] filePath;        //鏂囦欢璺緞
    private String downloadFilePath;  //鏂囦欢涓嬭浇璺緞
    private InputStream inputStream; 
    
    public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String[] getFilePath() {
		return filePath;
	}

	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}

	public String getDownloadFilePath() {
		return downloadFilePath;
	}

	public void setDownloadFilePath(String downloadFilePath) {
		this.downloadFilePath = downloadFilePath;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
     * @return
     */
	public String fileUpload() {
		String path = Constants.PROCESSING_FOLDER_PATH;
		System.out.println(path);
		File file = new File(path); 
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			if (this.file != null) {
				File f[] = this.getFile();
				filePath = new String[f.length];
				for (int i = 0; i < f.length; i++) {
					String fileName = java.util.UUID.randomUUID().toString(); 
					String name = fileName + fileFileName[i].substring(fileFileName[i].lastIndexOf("."));

					FileInputStream inputStream = new FileInputStream(f[i]);
					FileOutputStream outputStream = new FileOutputStream(path+ "\\" + name);
					byte[] buf = new byte[1024];
					int length = 0;
					while ((length = inputStream.read(buf)) != -1) {
						outputStream.write(buf, 0, length);
					}
					inputStream.close();
					outputStream.flush();
					filePath[i] = path + "\\" + name;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

    /***********************************************/
    // 跳转
    /***********************************************/
//    protected String unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//    	return fileUpload();
//    }
}