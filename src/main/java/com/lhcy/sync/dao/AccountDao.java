package com.lhcy.sync.dao;

import com.hongchen.kis.db.DbConnectionFactory;
import com.hongchen.kis.db.DbSqlHelper;
import com.lhcy.core.bo.*;
import com.lhcy.core.util.ConvertUtils;
import com.lhcy.core.util.StringUtils;
import com.lhcy.core.util.TreeListUtils;
import com.lhcy.sync.domain.dto.AccountDto;
import com.lhcy.sync.domain.pojo.Account;
import com.lhcy.sync.web.form.AccountForm;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountDao {
    private Logger logger = Logger.getLogger(AccountDao.class);

    /***********************************************/
    // 列表的总数量
    /***********************************************/
    public int count(AccountForm form) throws Exception {
        int result = 0;
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(1) as cnt ");
        sql.append("   from t_bd_Account a ");
        sql.append("  where 1=1 ");

        List args = new ArrayList();
        sql.append(getWhere(form, args));

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return result;
            }
            while(rs.next()){
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    /***********************************************/
    // 列表
    /***********************************************/
    public List<AccountDto> list(int rowBegin, int rowEnd, AccountForm form) throws Exception{

        List args = new ArrayList();
        String where = getWhere(form, args);

        String order = "asc";
        if (form.getOrder() != null && form.getOrder().length() > 0){
            order = form.getOrder();
        }

        String sort = "number";
        if (form.getSort() != null && form.getSort().length() > 0){
            sort = form.getSort();
        }

        if ("treeName".equalsIgnoreCase(sort)) {
            sort = "d.FName ";

        }else if ("createUserName".equalsIgnoreCase(sort)) {
            sort = "b.FName ";

        }else if ("lastUpdateUserName".equalsIgnoreCase(sort)) {
            sort = "c.FName ";

        }else{
            sort = "a.F" + sort + " ";
        }

        ArrayList<AccountDto> result = new ArrayList<AccountDto>();
        StringBuilder sql = new StringBuilder();
        sql.append(" WITH temp AS ( ");
        sql.append(" SELECT top 100 percent" );
        sql.append("     a.FID ");
        sql.append("    ,a.FNumber ");
        sql.append("    ,a.FName ");
        sql.append("    ,a.FShortNumber ");
        sql.append("    ,a.FShortName ");
        sql.append("    ,a.FTreeID ");
        sql.append("    ,d.FName as FTreeName ");
        sql.append("    ,a.FComment ");
        sql.append("    ,a.FEnable ");
        sql.append("    ,b.FName as FCreateUserName ");
        sql.append("    ,a.FCreateTime ");
        sql.append("    ,c.FName as FLastUpdateUserName ");
        sql.append("    ,a.FLastUpdateTime ");
        sql.append("    ,ROW_NUMBER() OVER (ORDER BY " + sort + " " + order + ") AS 'RowAccount'");
        sql.append("   FROM t_bd_Account a ");
        sql.append("   LEFT JOIN t_pm_user b on b.FID = a.FCreateUserID ");
        sql.append("   LEFT JOIN t_pm_user c on c.FID = a.FLastUpdateUserID ");
        sql.append("   LEFT JOIN t_bd_AccountTree d on d.FID = a.FTreeID ");
        sql.append("  WHERE 1=1 ");
        sql.append(where);
        sql.append(" ) ");
        sql.append(" SELECT * FROM temp ");
        sql.append(" WHERE RowAccount BETWEEN " + rowBegin + " AND " + rowEnd + " ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return result;
            }
            while(rs.next()){
                AccountDto vo = new AccountDto();
                vo.setId(rs.getString("FID"));
                vo.setNumber(HtmlRender.toHtml(rs.getString("FNumber")));
                vo.setName(HtmlRender.toHtml(rs.getString("FName")));
                vo.setShortNumber(HtmlRender.toHtml(rs.getString("FShortNumber")));
                vo.setShortName(HtmlRender.toHtml(rs.getString("FShortName")));
                vo.setTreeID(rs.getString("FTreeID"));
                vo.setTreeName(HtmlRender.toHtml(rs.getString("FTreeName")));
                vo.setEnable(rs.getInt("FEnable"));
                vo.setComment(HtmlRender.toHtml(rs.getString("FComment")));
                vo.setCreateUserName(HtmlRender.toHtml(rs.getString("FCreateUserName")));
                vo.setCreateTime(ConvertUtils.timestampToString(rs.getTimestamp("FCreateTime")));
                vo.setLastUpdateUserName(HtmlRender.toHtml(rs.getString("FLastUpdateUserName")));
                vo.setLastUpdateTime(ConvertUtils.timestampToString(rs.getTimestamp("FLastUpdateTime")));

                result.add(vo);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    /***********************************************/
    // 查询一个
    /***********************************************/
    public AccountDto query(String id) throws Exception{

        AccountDto result = new AccountDto();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("     a.FID ");
        sql.append("    ,a.FNumber ");
        sql.append("    ,a.FName ");
        sql.append("    ,a.FShortNumber ");
        sql.append("    ,a.FShortName ");
        sql.append("    ,d.FID as FTreeID ");
        sql.append("    ,d.FLongName as FTreeName ");
        sql.append("    ,a.FComment ");
        sql.append("    ,a.FEnable ");
        sql.append("    ,b.FName as FCreateUserName ");
        sql.append("    ,a.FCreateTime ");
        sql.append("    ,c.FName as FLastUpdateUserName ");
        sql.append("    ,a.FLastUpdateTime ");
        sql.append("   FROM t_bd_Account a ");
        sql.append("   LEFT JOIN t_pm_user b on b.FID = a.FCreateUserID ");
        sql.append("   LEFT JOIN t_pm_user c on c.FID = a.FLastUpdateUserID ");
        sql.append("   LEFT JOIN t_bd_AccountTree d on d.FID = a.FTreeID ");
        sql.append("  WHERE a.FID = ? ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        List args = new ArrayList();
        args.add(id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return result;
            }
            while(rs.next()){
                result.setId(rs.getString("FID"));
                result.setNumber(rs.getString("FNumber"));
                result.setName(rs.getString("FName"));
                result.setShortNumber(rs.getString("FShortNumber"));
                result.setShortName(rs.getString("FShortName"));
                result.setTreeID(rs.getString("FTreeID"));
                result.setTreeName(rs.getString("FTreeName"));
                result.setComment(rs.getString("FComment"));
                result.setEnable(rs.getInt("FEnable"));
                result.setCreateUserName(rs.getString("FCreateUserName"));
                result.setCreateTime(ConvertUtils.timestampToString(rs.getTimestamp("FCreateTime")));
                result.setLastUpdateUserName(rs.getString("FLastUpdateUserName"));
                result.setLastUpdateTime(ConvertUtils.timestampToString(rs.getTimestamp("FLastUpdateTime")));
            }
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

        return result;
    }

    /***********************************************/
    // 创建一个
    /***********************************************/
    public void create(Account vo) throws Exception{

        if (vo == null){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO t_bd_Account ( ");
        sql.append("     FID ");
        sql.append("    ,FNumber ");
        sql.append("    ,FName ");
        sql.append("    ,FShortNumber ");
        sql.append("    ,FShortName ");
        sql.append("    ,FTreeID ");
        sql.append("    ,FComment ");
        sql.append("    ,FEnable ");
        sql.append("    ,FCreateUserID ");
        sql.append("    ,FCreateTime ");
        sql.append("    ,FLastUpdateUserID ");
        sql.append("    ,FLastUpdateTime ");
        sql.append(" )VALUES(");
        sql.append(StringUtils.getSqlPlaceholder(12));
        sql.append(" )");

        List args = new ArrayList();
        args.add(vo.getId());
        args.add(vo.getNumber());
        args.add(vo.getName());
        args.add(vo.getShortNumber());
        args.add(vo.getShortName());
        args.add(vo.getTreeID());
        args.add(vo.getComment());
        args.add(vo.getEnable());
        args.add(vo.getCreateUserID());
        args.add(vo.getCreateTime());
        args.add(vo.getLastUpdateUserID());
        args.add(vo.getLastUpdateTime());

        try {
            executeNonQuery(sql.toString(), args);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /***********************************************/
    // 更新一个
    /***********************************************/
    public void update(Account vo) throws Exception{

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE t_bd_Account SET ");
        sql.append("   FNumber = ? ");
        sql.append("  ,FName = ? ");
        sql.append("  ,FTreeID = ? ");
        sql.append("  ,FShortNumber = ? ");
        sql.append("  ,FShortName = ? ");
        sql.append("  ,FComment = ? ");
        sql.append("  ,FEnable = ? ");
        sql.append("  ,FLastUpdateUserID = ? ");
        sql.append("  ,FLastUpdateTime = ? ");
        sql.append(" WHERE FID = ? ");

        List args = new ArrayList();
        args.add(vo.getNumber());
        args.add(vo.getName());
        args.add(vo.getTreeID());
        args.add(vo.getShortNumber());
        args.add(vo.getShortName());
        args.add(vo.getComment());
        args.add(vo.getEnable());
        args.add(vo.getLastUpdateUserID());
        args.add(vo.getLastUpdateTime());
        args.add(vo.getId());

        try {
            executeNonQuery(sql.toString(), args);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /***********************************************/
    // 移动至分类多个
    /***********************************************/
    public void move(String[] list, String tree, String user) throws Exception{

        if (tree == null || tree.length() == 0){
            return;
        }

        if (list == null || list.length == 0){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE t_bd_Account SET ");
        sql.append("     FTreeID = ? ");
        sql.append("    ,FLastUpdateUserID = ? ");
        sql.append("    ,FLastUpdateTime = ? ");
        sql.append(" WHERE FID in (" + StringUtils.listToCommaString(list) + ") ");

        List args = new ArrayList();
        args.add(tree);
        args.add(user);
        args.add(new Date());

        try {
            executeNonQuery(sql.toString(), args);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /***********************************************/
    // 删除多个
    /***********************************************/
    public void delete(String[] list) throws Exception{

        if (list == null || list.length == 0){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM t_bd_Account ");
        sql.append(" WHERE FID = ? ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return;
        }

        PreparedStatement ps = null;
        String pk = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql.toString());

            for(String id : list){
                List args = new ArrayList();
                args.add(id);
                pk = id;

                DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);
            }
            ps.executeBatch();
            conn.commit();

        } catch (SQLException e) {

            AccountDto dto = new AccountDto();
            try {
                dto = this.query(pk);
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                logger.error(e1.getMessage());
            }

            if (dto.getNumber() == null || dto.getNumber().length() == 0) {
                throw new Exception(e);
            } else {
                throw new Exception(e.getMessage() + "编号：" + dto.getNumber() + "<br/>");
            }
        }catch (Exception e){

            try{
                conn.rollback();
            }catch (Exception e1){
                e1.printStackTrace();
                logger.error(e1.getMessage());
            }

            throw new Exception(e);

        }finally{
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }

    /***********************************************/
    // 启用或禁用多个
    /***********************************************/
    public void enable(String[] list, int status, String user) throws Exception{

        if (list == null || list.length == 0){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE t_bd_Account SET ");
        sql.append("      FEnable = ? ");
        sql.append("     ,FLastUpdateUserID = ? ");
        sql.append("     ,FLastUpdateTime = ? ");
        sql.append(" WHERE FID in (" + StringUtils.listToCommaString(list) + ") ");

        List args = new ArrayList();
        args.add(status);
        args.add(user);
        args.add(new Date());

        try {
            executeNonQuery(sql.toString(), args);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /***********************************************/
    // 获得相同编号的数据
    /***********************************************/
    public AccountDto getEquals(String number) throws Exception{

        AccountDto result = new AccountDto();
        if (number == null || number.length() == 0){
            return result;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("    FNumber ");
        sql.append("   ,FName ");
        sql.append("   ,FID ");
        sql.append(" FROM t_bd_Account ");
        sql.append("WHERE FNumber = ? ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        List args = new ArrayList();
        args.add(number);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return result;
            }
            while(rs.next()){
                result.setId(rs.getString("FID"));
                result.setNumber(rs.getString("FNumber"));
                result.setName(rs.getString("FName"));
            }
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

        return result;
    }
    @SuppressWarnings("rawtypes")
   	public static void executeNonQuery(String sql, List args) throws SQLException{
   		
   		Connection conn = DbConnectionFactory.createHonchenConnection();
   		if (conn == null){
   			return;
   		}
   		PreparedStatement ps = null;
   		
   		try {
   			ps = conn.prepareStatement(sql.toString());
   			setParameter(ps, args);
   			ps.execute();
   		} catch (SQLException e) {
   			throw e;
   		}finally{
   			try {
   				ps.close();
   				conn.close();
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
   		}
   		
   	}
    @SuppressWarnings("rawtypes")
   	private static void setParameter(PreparedStatement ps, List args) throws SQLException{
   		
   		if (ps == null || args == null){
   			return;
   		}
   		
   		for(int i = 1; i < args.size() + 1; i ++){

               Object val = args.get(i - 1);

               if (val instanceof Date){
                   ps.setTimestamp(i, new Timestamp(((Date)val).getTime()));

               }else{
                   ps.setObject(i, val);
               }
   		}
   	}
    /***********************************************/
    // 列表的过滤条件
    /***********************************************/
    private String getWhere(AccountForm form, List args) throws Exception{

        StringBuilder result = new StringBuilder();
        if (form == null){
            return "";
        }

        // 包含子节点
        if (form.isShowChildren() && form.getTreeID() != null && form.getTreeID().length() > 0){
            AccountTreeDao dao = new AccountTreeDao();
            List<TreeNode> tree = dao.list();
            List<String> ids = TreeListUtils.getChildrenID(tree, form.getTreeID());
            result.append(" AND a.FTreeID in (" + StringUtils.listToCommaString(ids) + ") ");

            // 不包含子节点
        }else if (!form.isShowChildren() && form.getTreeID() != null && form.getTreeID().length() > 0){
            result.append(" AND a.FTreeID = ? ");
            args.add(form.getTreeID());
        }

        if (form.getFilterField() != null && form.getFilterField().length() > 0 && form.getFilterValue() != null && form.getFilterValue().length() > 0){

            if ("nm+cd".equals(form.getFilterField())){
                result.append(" AND (a.FName like ? OR a.FNumber like ? ) ");
                args.add("%" + form.getFilterValue().trim() + "%");
                args.add("%" + form.getFilterValue().trim() + "%");

            }else{
                result.append(" AND " + "a.F" + form.getFilterField().trim() + " like ? ");
                args.add("%" + form.getFilterValue().trim() + "%");
            }
        }

        if (form.getEnable() == 1){
            result.append(" AND a.FEnable = 1 ");
        }

        return result.toString();
    }
}
