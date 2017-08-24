package com.lhcy.sync.service;

import com.lhcy.sync.dao.ParkingDao;
import com.lhcy.sync.domain.dto.ParkingDto;
import com.lhcy.sync.domain.pojo.Parking;
import com.lhcy.sync.web.form.ParkingForm;

import java.util.List;

public class ParkingService {

    private ParkingDao dao = new ParkingDao();

    public List<ParkingDto> list(int rowBegin, int pageSize, ParkingForm form) throws Exception{
    return dao.list(rowBegin, pageSize, form);
    }
//
//    public int count(ParkingForm form) throws Exception {
//        return dao.count(form);
//    }
//


    public ParkingDto query(String id) throws Exception{
        return dao.query(id);
    }

    public void create(Parking vo) throws Exception{
        dao.create(vo);
    }

    public void update(Parking vo) throws Exception{
        dao.update(vo);
    }
//
//    public void move(String[] list, String tree, String user) throws Exception{
//        dao.move(list, tree, user);
//    }
//    
    public void delete(String[] list) throws Exception{
        dao.delete(list);
    }
//
//    public void enable(String[] list, int status, String user) throws Exception{
//        dao.enable(list, status, user);
//    }
//
    public ParkingDto getEquals(String licenseno) throws Exception{
        return dao.getEquals(licenseno);
    }

}
