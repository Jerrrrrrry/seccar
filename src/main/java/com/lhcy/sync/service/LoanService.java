package com.lhcy.sync.service;

import com.lhcy.sync.dao.LoanDao;
import com.lhcy.sync.domain.dto.LoanDto;
import com.lhcy.sync.domain.pojo.Loan;
import com.lhcy.sync.web.form.LoanForm;

import java.util.List;

public class LoanService {

    private LoanDao dao = new LoanDao();

    public List<LoanDto> list(int rowBegin, int pageSize, LoanForm form) throws Exception{
    return dao.list(rowBegin, pageSize, form);
    }

    public int count(LoanForm form) throws Exception {
        return dao.count(form);
    }

    public LoanDto query(String id) throws Exception{
        return dao.query(id);
    }

    public void create(Loan vo) throws Exception{
        dao.create(vo);
    }
    
//    public double getspare() throws Exception{
//        return dao.getspare();
//    }
//    
    public void update(Loan vo) throws Exception{
        dao.update(vo);
    }
//    
//    public void settle(Loan vo) throws Exception{
//        dao.settle(vo);
//    }
//    
//    public void updateSpare(double sloan) throws Exception{
//        dao.updateSpare(sloan);
//    }
//       
    public void deletesingle(String vehicleid) throws Exception{
        dao.deletesingle(vehicleid);
    }
//
//    public void delete(String[] list) throws Exception{
//        dao.delete(list);
//    }
////
////    public void enable(String[] list, int status, String user) throws Exception{
////        dao.enable(list, status, user);
////    }
////
    public LoanDto getEquals(String licenseno) throws Exception{
        return dao.getEquals(licenseno);
    }

}
