package com.lhcy.sync.service;

import com.lhcy.sync.dao.TradeDao;
import com.lhcy.sync.domain.dto.TradeDto;
import com.lhcy.sync.domain.pojo.Trade;
import com.lhcy.sync.web.form.TradeForm;

import java.util.List;

public class TradeService {

    private TradeDao dao = new TradeDao();

    public List<TradeDto> list(int rowBegin, int pageSize, TradeForm form, String accessType) throws Exception{
    return dao.list(rowBegin, pageSize, form, accessType);
    }

    public int count(TradeForm form, String accessType) throws Exception {
        return dao.count(form, accessType);
    }

    public TradeDto query(String id) throws Exception{
        return dao.query(id);
    }

    public void create(Trade vo) throws Exception{
        dao.create(vo);
    }
    
    public double getspare(String userId) throws Exception{
        return dao.getspare(userId);
    }
    
    public void update(Trade vo) throws Exception{
        dao.update(vo);
    }
    
    public void settle(Trade vo) throws Exception{
        dao.settle(vo);
    }
    
    public void updateSpare(String userId, double sloan) throws Exception{
        dao.updateSpare(userId, sloan);
    }
       
    public void deletesingle(String vehicleid) throws Exception{
        dao.deletesingle(vehicleid);
    }
////
////    public void move(String[] list, String tree, String user) throws Exception{
////        dao.move(list, tree, user);
////    }
////    
    public void delete(String[] list) throws Exception{
        dao.delete(list);
    }
////
////    public void enable(String[] list, int status, String user) throws Exception{
////        dao.enable(list, status, user);
////    }
////
    public TradeDto getEquals(String licenseno) throws Exception{
        return dao.getEquals(licenseno);
    }

}
