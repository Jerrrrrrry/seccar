package com.lhcy.sync.service;

import com.lhcy.sync.dao.ParkingDao;
import com.lhcy.sync.domain.dto.AccountDto;
import com.lhcy.sync.domain.dto.ParkingDto;
import com.lhcy.sync.domain.pojo.Account;
import com.lhcy.sync.web.form.AccountForm;
import com.lhcy.sync.web.form.ParkingForm;

import java.util.List;

public class ParkingService {

    private ParkingDao dao = new ParkingDao();

    public List<ParkingDto> list(int rowBegin, int pageSize, ParkingForm form) throws Exception{
    return dao.list(rowBegin, pageSize, form);
    }
//
//    public int count(AccountForm form) throws Exception {
//        return dao.count(form);
//    }
//
//    public List<AccountDto> list(int rowBegin, int pageSize, AccountForm form) throws Exception{
//        return dao.list(rowBegin, pageSize, form);
//    }
//
//    public AccountDto query(String id) throws Exception{
//        return dao.query(id);
//    }
//
//    public void create(Account vo) throws Exception{
//        dao.create(vo);
//    }
//
//    public void update(Account vo) throws Exception{
//        dao.update(vo);
//    }
//
//    public void move(String[] list, String tree, String user) throws Exception{
//        dao.move(list, tree, user);
//    }
//    
//    public void delete(String[] list) throws Exception{
//        dao.delete(list);
//    }
//
//    public void enable(String[] list, int status, String user) throws Exception{
//        dao.enable(list, status, user);
//    }
//
//    public AccountDto getEquals(String number) throws Exception{
//        return dao.getEquals(number);
//    }

}
