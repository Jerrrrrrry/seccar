package com.lhcy.sync.dao;

import com.hongchen.kis.db.DbConnectionFactory;
import com.hongchen.kis.db.DbSqlHelper;
import com.lhcy.core.bo.*;
import com.lhcy.core.util.ConvertUtils;
import com.lhcy.core.util.StringUtils;
import com.lhcy.core.util.TreeListUtils;
import com.lhcy.sync.domain.dto.ParkingDto;
import com.lhcy.sync.domain.dto.ParkingDto;
import com.lhcy.sync.domain.pojo.Parking;
import com.lhcy.sync.web.form.ParkingForm;
import com.lhcy.sync.web.form.ParkingForm;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingDao {
    private Logger logger = Logger.getLogger(ParkingDao.class);

    /***********************************************/
    // 列表的总数量
    /***********************************************/
//    public int count(ParkingForm form) throws Exception {
//        int result = 0;
//        StringBuilder sql = new StringBuilder();
//        sql.append(" select count(1) as cnt ");
//        sql.append("   from t_bd_Parking a ");
//        sql.append("  where 1=1 ");
//
//        List args = new ArrayList();
//        sql.append(getWhere(form, args));
//
//        Connection conn = DbConnectionFactory.createHonchenConnection();
//        if (conn == null){
//            return result;
//        }
//
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            ps = conn.prepareStatement(sql.toString());
//            rs = DbSqlHelper.executeQuery(ps, args);
//
//            if (rs == null){
//                return result;
//            }
//            while(rs.next()){
//                result = rs.getInt(1);
//            }
//        } catch (Exception e) {
//            throw new Exception(e);
//        }finally{
//            try {
//                rs.close();
//                ps.close();
//                conn.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//                logger.error(e.getMessage());
//            }
//        }
//        return result;
//    }

    /***********************************************/
    // 列表
    /***********************************************/
    public List<ParkingDto> list(int rowBegin, int rowEnd, ParkingForm form) throws Exception{

        List args = new ArrayList();
        String where = getWhere(form, args);

        String order = "desc";
        if (form.getOrder() != null && form.getOrder().length() > 0){
            order = form.getOrder();
        }

        String sort = "inventoryints";
        if (form.getSort() != null && form.getSort().length() > 0){
            sort = form.getSort();
        }


        ArrayList<ParkingDto> result = new ArrayList<ParkingDto>();
        StringBuilder sql = new StringBuilder();
        sql.append(" WITH temp AS ( ");
        sql.append(" SELECT top 100 percent" );
        sql.append("     a.vehicleid ");
        sql.append("    ,a.period ");
        sql.append("    ,a.customer ");
        sql.append("    ,a.cardescription ");
        sql.append("    ,a.licenseno ");
        sql.append("    ,a.inventoryints ");
        sql.append("    ,a.inventoryoutts ");
        sql.append("    ,a.parkingfee ");
        sql.append("    ,a.comments ");
        sql.append("    ,ROW_NUMBER() OVER (ORDER BY " + sort + " " + order + ") AS 'RowParking'");
        sql.append("   FROM Parking a ");
        sql.append("  WHERE 1=1 ");
        sql.append(where);
        sql.append(" ) ");
        sql.append(" SELECT * FROM temp ");
        sql.append(" WHERE RowParking BETWEEN " + rowBegin + " AND " + rowEnd + " ");
        System.out.println("SQL: "+sql);
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
                ParkingDto vo = new ParkingDto();
                vo.setVehicleid(rs.getString("vehicleid"));
                vo.setPeriod(rs.getString("period"));
                vo.setCustomer(rs.getString("customer"));
                vo.setCardescription(rs.getString("cardescription"));
                vo.setLicenseno(rs.getString("licenseno"));
                vo.setInventoryints(rs.getString("inventoryints"));
                vo.setInventoryoutts(rs.getString("inventoryoutts"));
                vo.setParkingfee(rs.getDouble("parkingfee"));
                vo.setComments(rs.getString("comments"));

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
    public ParkingDto query(String id) throws Exception{

        ParkingDto result = new ParkingDto();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("     a.vehicleid ");
        sql.append("    ,a.period ");
        sql.append("    ,a.customer ");
        sql.append("    ,a.cardescription ");
        sql.append("    ,a.licenseno ");
        sql.append("    ,a.inventoryints ");
        sql.append("    ,a.inventoryoutts ");
        sql.append("    ,a.parkingfee ");
        sql.append("    ,a.comments ");
        sql.append("    ,a.createdts ");
        sql.append("    ,a.lastupdatedts ");
        sql.append("    ,a.creator ");
        sql.append("   FROM Parking a ");
        sql.append("  WHERE a.vehicleid = ? ");

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
                result.setVehicleid(rs.getString("vehicleid"));
                result.setPeriod(rs.getString("period"));
                result.setCustomer(rs.getString("customer"));
                result.setCardescription(rs.getString("cardescription"));
                result.setLicenseno(rs.getString("licenseno"));
                result.setInventoryints(rs.getString("inventoryints"));
                result.setInventoryoutts(rs.getString("inventoryoutts"));
                result.setParkingfee(rs.getDouble("parkingfee"));
                result.setComments(rs.getString("comments"));
                result.setCreatedts(ConvertUtils.timestampToString(rs.getTimestamp("createdts")));
                result.setLastupdatedts(ConvertUtils.timestampToString(rs.getTimestamp("lastupdatedts")));
                result.setCreator(rs.getString("creator"));
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
    public void create(Parking vo) throws Exception{

        if (vo == null){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO Parking ( ");
        sql.append("     vehicleid ");
        sql.append("    ,period ");
        sql.append("    ,customer ");
        sql.append("    ,cardescription ");
        sql.append("    ,licenseno ");
        sql.append("    ,inventoryints ");
        sql.append("    ,inventoryoutts ");
        sql.append("    ,parkingfee ");
        sql.append("    ,comments ");
        sql.append("    ,createdts ");
        sql.append("    ,lastupdatedts ");
        sql.append("    ,creator ");
        sql.append(" )VALUES(");
        sql.append(StringUtils.getSqlPlaceholder(12));
        sql.append(" )");

        List args = new ArrayList();
        args.add(vo.getVehicleid());
        args.add(vo.getPeriod());
        args.add(vo.getCustomer());
        args.add(vo.getCardescription());
        args.add(vo.getLicenseno());
        args.add(vo.getInventoryints());
        args.add(vo.getInventoryoutts());
        args.add(vo.getParkingfee());
        args.add(vo.getComments());
        args.add(new Date());
        args.add(new Date());
        args.add("SYSTEM");

        try {
            executeNonQuery(sql.toString(), args);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /***********************************************/
    // 更新一个
    /***********************************************/
    public void update(Parking vo) throws Exception{

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE Parking SET ");
        sql.append("   period = ? ");
        sql.append("  ,customer = ? ");
        sql.append("  ,cardescription = ? ");
        sql.append("  ,licenseno = ? ");
        sql.append("  ,inventoryints = ? ");
        sql.append("  ,inventoryoutts = ? ");
        sql.append("  ,parkingfee = ? ");
        sql.append("  ,comments = ? ");
        sql.append("  ,lastupdatedts = ? ");
        sql.append(" WHERE vehicleid = ? ");

        List args = new ArrayList();
        args.add(vo.getPeriod());
        args.add(vo.getCustomer());
        args.add(vo.getCardescription());
        args.add(vo.getLicenseno());
        args.add(vo.getInventoryints());
        args.add(vo.getInventoryoutts());
        args.add(vo.getParkingfee());
        args.add(vo.getComments());
        args.add(new Date());
        args.add(vo.getVehicleid());

        try {
            executeNonQuery(sql.toString(), args);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
//
//    /***********************************************/
//    // 移动至分类多个
//    /***********************************************/
//    public void move(String[] list, String tree, String user) throws Exception{
//
//        if (tree == null || tree.length() == 0){
//            return;
//        }
//
//        if (list == null || list.length == 0){
//            return;
//        }
//
//        StringBuilder sql = new StringBuilder();
//        sql.append(" UPDATE t_bd_Parking SET ");
//        sql.append("     FTreeID = ? ");
//        sql.append("    ,FLastUpdateUserID = ? ");
//        sql.append("    ,FLastUpdateTime = ? ");
//        sql.append(" WHERE FID in (" + StringUtils.listToCommaString(list) + ") ");
//
//        List args = new ArrayList();
//        args.add(tree);
//        args.add(user);
//        args.add(new Date());
//
//        try {
//            executeNonQuery(sql.toString(), args);
//
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }

    /***********************************************/
    // 删除多个
    /***********************************************/
    public void delete(String[] list) throws Exception{

        if (list == null || list.length == 0){
            return;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM Parking ");
        sql.append(" WHERE vehicleid = ? ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return;
        }

        PreparedStatement ps = null;
        String pk = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql.toString());

            for(String vehicleid : list){
                List args = new ArrayList();
                args.add(vehicleid);
                pk = vehicleid;

                DbSqlHelper.executeNonQueryWithBatch(conn, ps, args);
            }
            ps.executeBatch();
            conn.commit();

        } catch (SQLException e) {

            ParkingDto dto = new ParkingDto();
            try {
                dto = this.query(pk);
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                logger.error(e1.getMessage());
            }

            if (dto.getLicenseno() == null || dto.getLicenseno().length() == 0) {
                throw new Exception(e);
            } else {
                throw new Exception(e.getMessage() + "车辆：" + dto.getLicenseno() + "<br/>");
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
//
//    /***********************************************/
//    // 启用或禁用多个
//    /***********************************************/
//    public void enable(String[] list, int status, String user) throws Exception{
//
//        if (list == null || list.length == 0){
//            return;
//        }
//
//        StringBuilder sql = new StringBuilder();
//        sql.append(" UPDATE t_bd_Parking SET ");
//        sql.append("      FEnable = ? ");
//        sql.append("     ,FLastUpdateUserID = ? ");
//        sql.append("     ,FLastUpdateTime = ? ");
//        sql.append(" WHERE FID in (" + StringUtils.listToCommaString(list) + ") ");
//
//        List args = new ArrayList();
//        args.add(status);
//        args.add(user);
//        args.add(new Date());
//
//        try {
//            executeNonQuery(sql.toString(), args);
//
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }
//
    /***********************************************/
    // 获得相同编号的数据
    /***********************************************/
    public ParkingDto getEquals(String licenseno) throws Exception{

        ParkingDto result = new ParkingDto();
        if (licenseno == null || licenseno.length() == 0){
            return result;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("    licenseno ");
        sql.append("   ,licenseno ");
        sql.append("   ,customer ");
        sql.append("   ,vehicleid ");
        sql.append(" FROM Parking ");
        sql.append("WHERE licenseno = ? ");

        Connection conn = DbConnectionFactory.createHonchenConnection();
        if (conn == null){
            return result;
        }

        List args = new ArrayList();
        args.add(licenseno);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            rs = DbSqlHelper.executeQuery(ps, args);

            if (rs == null){
                return result;
            }
            while(rs.next()){
                result.setVehicleid(rs.getString("vehicleid"));
                result.setLicenseno(rs.getString("licenseno"));
                result.setCustomer(rs.getString("customer"));
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
    private String getWhere(ParkingForm form, List args) throws Exception{

        StringBuilder result = new StringBuilder();
        if (form == null){
            return "";
        }


        if (form.getFilterField() != null && form.getFilterField().length() > 0 ){

            if ("nm+cd".equals(form.getFilterField()) && form.getFilterValue() != null && form.getFilterValue().length() > 0){
                result.append(" AND (a.customer like ? OR a.licenseno like ? ) ");
                args.add("%" + form.getFilterValue().trim() + "%");
                args.add("%" + form.getFilterValue().trim() + "%");

            }else{
            	if(form.getFiltercustomer() != null && form.getFiltercustomer().length() > 0){
	                result.append(" AND (a.customer like ? ) ");
	                args.add("%" + form.getFiltercustomer().trim() + "%");
	            	}
            	if(form.getFilterlicenseno() != null && form.getFilterlicenseno().length() > 0){
                    result.append(" AND (a.licenseno like ? ) ");
                    args.add("%" + form.getFilterlicenseno().trim() + "%");
                	}
            	if(form.getFiltercardescription() != null && form.getFiltercardescription().length() > 0){
                    result.append(" AND (a.cardescription like ? ) ");
                    args.add("%" + form.getFiltercardescription().trim() + "%");
                	}
            	if(form.getFilterinventoryints() != null && form.getFilterinventoryints().length() > 0){
                    result.append(" AND (a.inventoryints >= ? ) ");
                    args.add(form.getFilterinventoryints().trim());
                	}
            	if(form.getFilterinventoryoutts() != null && form.getFilterinventoryoutts().length() > 0){
                    result.append(" AND (a.inventoryoutts <= ? ) ");
                    args.add(form.getFilterinventoryoutts().trim());
                	}
                			 	
            }
        }

//        if (form.getEnable() == 1){
//            result.append(" AND a.FEnable = 1 ");
//        }

        return result.toString();
    }
}
