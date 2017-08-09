package com.lhcy.platform.dao;


import com.hongchen.kis.db.DbConnectionFactory;
import com.hongchen.kis.db.DbSqlHelper;
import com.lhcy.core.bo.HtmlRender;
import com.lhcy.core.util.ConvertUtils;
import com.lhcy.core.util.EncryptUtils;
import com.lhcy.core.util.StringUtils;
import com.lhcy.platform.domain.dto.UserDto;
import com.lhcy.platform.domain.pojo.User;
import com.lhcy.platform.web.form.UserForm;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {

    private Logger logger = Logger.getLogger(UserDao.class);
   

    /***********************************************/
    /**
     * @throws Exception *********************************************/
    public int count(UserForm form) throws Exception {
        int result = 0;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT count(1) as cnt ");
        sql.append("   FROM Users a ");
        sql.append("  WHERE a.userid != 'superman' ");

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
            throw e;
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
    // 鍒楄〃
    /**
     * @throws Exception *********************************************/
    public List<UserDto> list(int rowBegin, int rowEnd, UserForm form) throws Exception {

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
        sort = "a.F" + sort;
        
        ArrayList<UserDto> result = new ArrayList<UserDto>();
        StringBuilder sql = new StringBuilder();
        sql.append(" WITH temp AS ( ");
        sql.append(" SELECT top 100 percent" );
        sql.append("     a.userid ");
        sql.append("    ,a.username ");
        sql.append("    ,a.islocked ");
        sql.append("    ,a.accesstype ");
        sql.append("    ,a.comments ");
        sql.append("    ,a.createdts ");
        sql.append("    ,a.lastupdatedts ");
        sql.append("    ,ROW_NUMBER() OVER (ORDER BY " + sort + " " + order + ") AS 'RowAccount'");
        sql.append("   FROM Users a ");
        sql.append("  WHERE a.userid != 'superman' ");
        sql.append(where);
        sql.append(" ) ");
        sql.append(" SELECT * FROM temp ");
        sql.append(" WHERE RowAccount BETWEEN " + rowBegin + " AND " + rowEnd + " ");

        sql.append("     a.userid ");
        sql.append("    ,a.username ");
        sql.append("    ,a.islocked ");
        sql.append("    ,a.accesstype ");
        sql.append("    ,a.comments ");
        sql.append("    ,a.createdts ");
        sql.append("    ,a.lastupdatedts ");
        sql.append("   FROM Users a ");
        sql.append("  WHERE a.userid = ? ");
        
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
                UserDto vo = new UserDto();
                vo.setId(rs.getString("userid"));
                vo.setAccount(HtmlRender.toHtml(rs.getString("username")));
                vo.setLocked(rs.getInt("islocked"));
                vo.setComment(HtmlRender.toHtml(rs.getString("comments")));
                vo.setCreateTime(ConvertUtils.timestampToString(rs.getTimestamp("createdts")));
                vo.setUpdateTime(ConvertUtils.timestampToString(rs.getTimestamp("lastupdatedts")));

                result.add(vo);
            }
        } catch (Exception e) {
            throw e;
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
    // 鏌ヨ涓�涓�
    /**
     * @throws Exception *********************************************/
    public UserDto query(String id) throws Exception {

        UserDto result = new UserDto();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("     a.userid ");
        sql.append("    ,a.username ");
        sql.append("    ,a.islocked ");
        sql.append("    ,a.accesstype ");
        sql.append("    ,a.comments ");
        sql.append("    ,a.createdts ");
        sql.append("    ,a.lastupdatedts ");
        sql.append("   FROM Users a ");
        sql.append("  WHERE a.userid = ? ");

        
        
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
                result.setId(rs.getString("userid"));
                result.setName(rs.getString("username"));
                result.setLocked(rs.getInt("islocked"));
                result.setComment(rs.getString("comments"));
                result.setCreateTime(ConvertUtils.timestampToString(rs.getTimestamp("createdts")));
                result.setUpdateTime(ConvertUtils.timestampToString(rs.getTimestamp("lastupdatedts")));
            }
        } catch (Exception e) {
            throw e;
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
    /***********************************************/
 
    
    /**SELECT [userid]
      ,[username]
      ,[password]
      ,[accesstype]
      ,[islocked]
      ,[creator]
      ,[createdts]
      ,[lastupdatedts]
      ,[userdesc]
      ,[comments]
      ,[sbcol1]
      ,[sbcol2]
      ,[sbcol3]
     * @throws Exception *********************************************/
    @SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public void create(User vo) throws Exception {

        if (vo == null){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO Users ( ");
        sql.append("     userid ");
        sql.append("    ,username ");
        sql.append("    ,password ");
        sql.append("    ,accesstype ");
        sql.append("    ,islocked ");
        sql.append("    ,creator ");
        sql.append("    ,createdts ");
        sql.append("    ,lastupdatedts ");
        sql.append("    ,userdesc ");
        sql.append("    ,comments ");
        sql.append(" )VALUES(");
        sql.append(StringUtils.getSqlPlaceholder(10));
        sql.append(" )");

        List args = new ArrayList();
        args.add(vo.getId());
        args.add(vo.getName());
        args.add(vo.getPassword());
        args.add(vo.getAccountType());
        args.add(vo.getLocked());
        args.add("test");
        args.add(new Date());
        args.add(new Date());
        args.add("d");
        args.add(vo.getComment());

        try {
        	this.executeNonQuery(sql.toString(), args);
    		
        } catch (Exception e) {
            throw e;
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
    /**
     * @throws Exception *********************************************/
    public void update(User vo) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE Users SET ");
        sql.append("    ");
        sql.append("  username = ? ");
        sql.append("  ,islocked = ? ");
        sql.append("  ,password = ? ");
        sql.append("  ,comments = ? ");
        sql.append("  ,lastupdatedts = ? ");
        sql.append(" WHERE userid = ? ");

        List args = new ArrayList();
        args.add(vo.getAccount());
        args.add(vo.getName());
        args.add(vo.getLocked());
        args.add(EncryptUtils.getMd5String(vo.getPassword()));
        args.add(vo.getComment());
        args.add(new Date());
        args.add(vo.getId());
        
        try {
            executeNonQuery(sql.toString(), args);

        } catch (Exception e) {
            throw e;
        }
    }

    /***********************************************/
    // 鍒犻櫎澶氫釜
    /**
     * @throws Exception *********************************************/
    public void delete(String[] list) throws Exception {

        if (list == null || list.length == 0){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM Users ");
        sql.append(" WHERE userid = ? ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return;
        }

        PreparedStatement ps = null;
        String pk = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql.toString());

            for(String vo : list){
                List args = new ArrayList();
                args.add(vo);
                pk = vo;

                DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);
            }
            ps.executeBatch();
            conn.commit();

        } catch (SQLException e) {

            UserDto dto = new UserDto();
            try {
                dto = this.query(pk);
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                logger.error(e1.getMessage());
            }

            if (dto.getAccount() == null || dto.getAccount().length() == 0) {
                throw e;
            } else {
                throw new Exception(e.getMessage() + "错误" + dto.getAccount() + "<br/>");
            }

        } catch (Exception e){

            try{
                conn.rollback();
            }catch (Exception e1){
                e1.printStackTrace();
                logger.error(e1.getMessage());
            }

            throw e;

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
    // 鍚敤鎴栫鐢ㄥ涓�
    /**
     * @throws Exception *********************************************/
    public void enable(String[] list, int status, String user) throws Exception {

        if (list == null || list.length == 0){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE Users SET ");
        sql.append("      islocked = ? ");
        sql.append(" WHERE userid in (" + StringUtils.listToCommaString(list) + ") ");

        List args = new ArrayList();
        args.add(status);

        try {
            executeNonQuery(sql.toString(), args);

        } catch (Exception e) {
            throw e;
        }
    }

    /***********************************************/
    // 鑾峰緱鐩稿悓缂栧彿鐨勬暟鎹�
    /**
     * @throws Exception *********************************************/
    public UserDto getEquals(String number) throws Exception {

        UserDto result = new UserDto();
        if (number == null || number.length() == 0){
            return result;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("    FAccount ");
        sql.append("   ,FName ");
        sql.append("   ,FID ");
        sql.append("  FROM Users ");
        sql.append(" WHERE FAccount = ? ");

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
                result.setAccount(rs.getString("FAccount"));
                result.setName(rs.getString("FName"));
            }
        } catch (Exception e) {
            throw e;
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
    // 鍒楄〃鐨勮繃婊ゆ潯浠�
    /***********************************************/
    private String getWhere(UserForm form, List args)  {

        StringBuilder result = new StringBuilder();
        if (form == null){
            return "";
        }

        if (form.getFilterField() != null && form.getFilterField().length() > 0 && form.getFilterValue() != null && form.getFilterValue().length() > 0){

            if ("nm+cd".equals(form.getFilterField())){
                result.append(" AND (a.username like ? OR a.userid like ? ) ");
                args.add("%" + form.getFilterValue().trim() + "%");
                args.add("%" + form.getFilterValue().trim() + "%");

            }else{
                result.append(" AND " + "a." + form.getFilterField().trim() + " like ? ");
                args.add("%" + form.getFilterValue().trim() + "%");
            }
        }

        return result.toString();
    }

    public User loginCheck(String account, String password) throws SQLException  {

        User result = new User();
        StringBuffer sql = new StringBuffer();
        sql.append(" select ");
        sql.append("     userid ");
        sql.append("    ,username ");
        sql.append("    ,accesstype ");
        sql.append("    ,islocked ");
        sql.append("    ,accesstype ");
        sql.append("  from Users ");
        sql.append(" where userid = ? ");
        sql.append("   and ISNULL(password, '') = ? ");

        String pwd = password == null || password.length() == 0 ? "" : password;//EncryptUtils.getMd5String(password);
        List args = new ArrayList();
        args.add(account);
        args.add(pwd);

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null) {
            return result;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null) {
                return result;
            }
            while (rs.next()) {
                result.setId(rs.getString("userid"));
                result.setAccountType(rs.getString("accesstype"));
                result.setName(rs.getString("username"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    public void chgPwd(User vo) throws SQLException {

        if (vo == null) {
            return;
        }

        StringBuffer sql = new StringBuffer();
        sql.append(" update Users ");
        sql.append("    set  password = ? ");
        sql.append("   where userid = ?  ");

        List<Object> paras = new ArrayList<Object>();
        paras.add(vo.getPassword());
        paras.add(vo.getId());

        try {
            executeNonQuery(sql.toString(), paras);

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
