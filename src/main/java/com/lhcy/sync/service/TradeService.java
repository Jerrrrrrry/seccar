package com.lhcy.sync.service;

import com.lhcy.sync.dao.TradeDao;
import com.lhcy.sync.domain.dto.TradeDto;
import com.lhcy.sync.domain.pojo.Trade;
import com.lhcy.sync.web.form.TradeForm;

import java.util.List;

public class TradeService {

    private TradeDao dao = new TradeDao();

    public List<TradeDto> list(int rowBegin, int pageSize, TradeForm form) throws Exception{
    return dao.list(rowBegin, pageSize, form);
    }

    public int count(TradeForm form) throws Exception {
        return dao.count(form);
    }

    public TradeDto query(String id) throws Exception{
        return dao.query(id);
    }

    public void create(Trade vo) throws Exception{
        dao.create(vo);
    }
    
    public double getspare() throws Exception{
        return dao.getspare();
    }
    
    public void update(Trade vo) throws Exception{
        dao.update(vo);
    }
    
    public void settle(Trade vo) throws Exception{
        dao.settle(vo);
    }
    
    public void updateSpare(double sloan) throws Exception{
        dao.updateSpare(sloan);
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
