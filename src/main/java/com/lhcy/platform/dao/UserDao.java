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
    // 鍒楄〃鐨勬�绘暟閲�
    /**
     * @throws Exception *********************************************/
    public int count(UserForm form) throws Exception {
        int result = 0;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT count(1) as cnt ");
        sql.append("   FROM t_pm_User a ");
        sql.append("  WHERE a.FAccount != 'superman' ");

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
        sql.append("     a.FID ");
        sql.append("    ,a.FAccount ");
        sql.append("    ,a.FName ");
        sql.append("    ,a.FEnable ");
        sql.append("    ,a.FComment ");
        sql.append("    ,a.FCreateTime ");
        sql.append("    ,a.FUpdateTime ");
        sql.append("    ,ROW_NUMBER() OVER (ORDER BY " + sort + " " + order + ") AS 'RowAccount'");
        sql.append("   FROM t_pm_User a ");
        sql.append("  WHERE a.FAccount != 'superman' ");
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
                UserDto vo = new UserDto();
                vo.setId(rs.getString("FID"));
                vo.setAccount(HtmlRender.toHtml(rs.getString("FAccount")));
                vo.setName(HtmlRender.toHtml(rs.getString("FName")));
                vo.setEnable(rs.getInt("FEnable"));
                vo.setComment(HtmlRender.toHtml(rs.getString("FComment")));
                vo.setCreateTime(ConvertUtils.timestampToString(rs.getTimestamp("FCreateTime")));
                vo.setUpdateTime(ConvertUtils.timestampToString(rs.getTimestamp("FUpdateTime")));

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
        sql.append("     a.FID ");
        sql.append("    ,a.FAccount ");
        sql.append("    ,a.FName ");
        sql.append("    ,a.FEnable ");
        sql.append("    ,a.FComment ");
        sql.append("    ,a.FCreateTime ");
        sql.append("    ,a.FUpdateTime ");
        sql.append("   FROM t_pm_User a ");
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
                result.setAccount(rs.getString("FAccount"));
                result.setName(rs.getString("FName"));
                result.setEnable(rs.getInt("FEnable"));
                result.setComment(rs.getString("FComment"));
                result.setCreateTime(ConvertUtils.timestampToString(rs.getTimestamp("FCreateTime")));
                result.setUpdateTime(ConvertUtils.timestampToString(rs.getTimestamp("FUpdateTime")));
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
    // 鍒涘缓涓�涓�
    /**
     * @throws Exception *********************************************/
    @SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public void create(User vo) throws Exception {

        if (vo == null){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO t_pm_User ( ");
        sql.append("     FID ");
        sql.append("    ,FAccount ");
        sql.append("    ,FName ");
        sql.append("    ,FEnable ");
        sql.append("    ,FPassword ");
        sql.append("    ,FComment ");
        sql.append("    ,FCreateTime ");
        sql.append("    ,FUpdateTime ");
        sql.append(" )VALUES(");
        sql.append(StringUtils.getSqlPlaceholder(8));
        sql.append(" )");

        List args = new ArrayList();
        args.add(vo.getId());
        args.add(vo.getAccount());
        args.add(vo.getName());
        args.add(vo.getEnable());
        args.add(EncryptUtils.getMd5String(vo.getPassword()));
        args.add(vo.getComment());
        args.add(new Date());
        args.add(new Date());

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
    // 鏇存柊涓�涓�
    /**
     * @throws Exception *********************************************/
    public void update(User vo) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE t_pm_User SET ");
        sql.append("   FAccount = ? ");
        sql.append("  ,FName = ? ");
        sql.append("  ,FEnable = ? ");
        sql.append("  ,FPassword = ? ");
        sql.append("  ,FComment = ? ");
        sql.append("  ,FUpdateTime = ? ");
        sql.append(" WHERE FID = ? ");

        List args = new ArrayList();
        args.add(vo.getAccount());
        args.add(vo.getName());
        args.add(vo.getEnable());
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
        sql.append(" DELETE FROM t_pm_User ");
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
                throw new Exception(e.getMessage() + "缂栧彿锛�" + dto.getAccount() + "<br/>");
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
        sql.append(" UPDATE t_pm_User SET ");
        sql.append("      FEnable = ? ");
        sql.append(" WHERE FID in (" + StringUtils.listToCommaString(list) + ") ");

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
        sql.append("  FROM t_pm_User ");
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
                result.append(" AND (a.FName like ? OR a.FAccount like ? ) ");
                args.add("%" + form.getFilterValue().trim() + "%");
                args.add("%" + form.getFilterValue().trim() + "%");

            }else{
                result.append(" AND " + "a.F" + form.getFilterField().trim() + " like ? ");
                args.add("%" + form.getFilterValue().trim() + "%");
            }
        }

        return result.toString();
    }

    public User loginCheck(String account, String password) throws SQLException  {

        User result = new User();
        StringBuffer sql = new StringBuffer();
        sql.append(" select ");
        sql.append("     FID ");
        sql.append("    ,FAccount ");
        sql.append("    ,FName ");
        sql.append("  from t_pm_User ");
        sql.append(" where FAccount = ? ");
        sql.append("   and FEnable = 1 ");
        sql.append("   and ISNULL(FPassword, '') = ? ");

        String pwd = password == null || password.length() == 0 ? "" : EncryptUtils.getMd5String(password);
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
                result.setId(rs.getString("FID"));
                result.setAccount(rs.getString("FAccount"));
                result.setName(rs.getString("FName"));
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
        sql.append(" update t_pm_User ");
        sql.append("    set  FPassword = ? ");
        sql.append("   where FId = ?  ");

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
