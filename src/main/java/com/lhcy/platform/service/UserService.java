package com.lhcy.platform.service;

import com.lhcy.platform.dao.UserDao;
import com.lhcy.platform.domain.dto.UserDto;
import com.lhcy.platform.domain.pojo.User;
import com.lhcy.platform.web.form.UserForm;

import java.util.List;

public class UserService {

    private UserDao dao = new UserDao();


    public int count(UserForm form) throws Exception{
        return dao.count(form);
    }

    public List<UserDto> list(int rowBegin, int rowEnd, UserForm form) throws Exception{
        return dao.list(rowBegin, rowEnd, form);
    }

    public UserDto query(String id) throws Exception{
        return dao.query(id);
    }
//
//    public void create(User vo) throws Exception{
//        dao.create(vo);
//    }
//
//    public void update(User vo) throws Exception{
//        dao.update(vo);
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
//    public UserDto getEquals(String number) throws Exception{
//        return dao.getEquals(number);
//    }

    public User loginCheck(String account, String password) throws Exception{
        return dao.loginCheck(account, password);
    }

    public void chgPwd(User vo) throws Exception{
        dao.chgPwd(vo);
    }

}
