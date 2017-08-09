package com.lhcy.sync.dao;

import com.hongchen.kis.db.DbConnectionFactory;
import com.hongchen.kis.db.DbSqlHelper;
import com.lhcy.core.bo.*;
import com.lhcy.core.util.StringUtils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountTreeDao {

    private Logger logger = Logger.getLogger(AccountTreeDao.class);

    /***********************************************/
    // 树
    /***********************************************/
    public List<TreeNode> list() throws Exception {

        ArrayList<TreeNode> result = new ArrayList<TreeNode>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT");
        sql.append("     FID ");
        sql.append("    ,FNumber ");
        sql.append("    ,FName ");
        sql.append("    ,FLongName ");
        sql.append("    ,FParentID ");
        sql.append("   FROM t_bd_AccountTree ");
        sql.append("  ORDER by FNumber ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps);

            if (rs == null){
                return result;
            }
            while(rs.next()){
                TreeNode vo = new TreeNode();
                vo.setId(rs.getString("FID"));
                vo.setNumber(HtmlRender.toHtml(rs.getString("FNumber")));
                vo.setName(HtmlRender.toHtml(rs.getString("FName")));
                vo.setLongName(HtmlRender.toHtml(rs.getString("FLongName")));
                vo.setParentID(rs.getString("FParentID"));

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
    // 取得树一个节点
    /***********************************************/
    public TreeNode query(String id) throws Exception{

        TreeNode result = new TreeNode();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("     a.FID ");
        sql.append("    ,a.FNumber ");
        sql.append("    ,a.FName ");
        sql.append("    ,a.FParentID ");
        sql.append("    ,b.FLongName as FParentName ");
        sql.append("   FROM t_bd_AccountTree a ");
        sql.append("   LEFT JOIN t_bd_AccountTree b on a.FParentID = b.FID ");
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
                result.setParentID(rs.getString("FParentID"));
                result.setParentName(rs.getString("FParentName"));
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
    // 创建一个树节点
    /***********************************************/
    public void create(TreeNode vo) throws Exception{

        if(vo == null || vo.getId() == null){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO t_bd_AccountTree ( ");
        sql.append("     FID ");
        sql.append("    ,FNumber ");
        sql.append("    ,FName ");
        sql.append("    ,FLongName ");
        sql.append("    ,FParentID ");
        sql.append("    ,FUserDefined ");
        sql.append(" )VALUES(");
        sql.append(StringUtils.getSqlPlaceholder(6));
        sql.append(" )");

        List args = new ArrayList();
        args.add(vo.getId());
        args.add(vo.getNumber());
        args.add(vo.getName());
        args.add(vo.getNumber() + " (" + vo.getName() + ")");
        args.add(vo.getParentID());
        args.add(1);

        try {
            executeNonQuery(sql.toString(), args);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /***********************************************/
    // 更新一个树节点
    /***********************************************/
    public void update(TreeNode vo) throws Exception{

        if(vo == null || vo.getId() == null){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE t_bd_AccountTree SET ");
        sql.append("    FNumber = ? ");
        sql.append("   ,FName = ? ");
        sql.append("   ,FLongName = ? ");
        sql.append("   ,FParentID = ? ");
        sql.append(" WHERE FID = ? ");
        sql.append("   AND FUserDefined = 1 ");

        List args = new ArrayList();
        args.add(vo.getNumber());
        args.add(vo.getName());
        args.add(vo.getNumber() + " (" + vo.getName() + ")");
        args.add(vo.getParentID());
        args.add(vo.getId());

        try {
            executeNonQuery(sql.toString(), args);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /***********************************************/
    // 删除一个树节点
    /***********************************************/
    public void delete(String id) throws Exception{

        if (id == null || id.length() == 0){
            return;
        }

        if (hasChildren(id)){
            throw new Exception(SysConstant.M_PK_ERROR);
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM t_bd_AccountTree ");
        sql.append("  WHERE FID = ? ");
        sql.append("   AND FUserDefined = 1 ");

        List args = new ArrayList();
        args.add(id);

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return;
        }

        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql.toString());
            DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);

            ps.executeBatch();
            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
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
    // 判断此节点下是否有子节点
    /***********************************************/
    public boolean hasChildren(String id) throws Exception{
        if (id == null || id.length() == 0){
            return false;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT count(1) as FCnt ");
        sql.append("   FROM t_bd_AccountTree ");
        sql.append("  WHERE FParentID = ?  ");

        List args = new ArrayList();
        args.add(id);

        int count = 0;
        try {
            executeQueryResultInt(sql.toString(), args);

        } catch (Exception e) {
            throw new Exception(e);
        }

        if (count > 0){
            return true;
        }

        return false;
    }

    /***********************************************/
    // 获得相同编号的数据
    /***********************************************/
    public TreeNode getEquals(String number) throws Exception{

        TreeNode result = new TreeNode();
        if (number == null || number.length() == 0){
            return result;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("    FNumber ");
        sql.append("   ,FName ");
        sql.append("   ,FID ");
        sql.append(" FROM t_bd_AccountTree ");
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
    @SuppressWarnings("rawtypes")
	public static int executeQueryResultInt(String sql, List args) throws SQLException{

		int result = 0;
		Connection conn = DbConnectionFactory.createHonchenConnection();
		if (conn == null){
			return result;
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql.toString());
			setParameter(ps, args);
			rs = ps.executeQuery();
			if (rs == null){
				return result;
			}
			while(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
