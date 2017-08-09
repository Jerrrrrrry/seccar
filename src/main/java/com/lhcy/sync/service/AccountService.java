package com.lhcy.sync.service;


import com.lhcy.core.bo.TreeNode;
import com.lhcy.sync.dao.AccountDao;
import com.lhcy.sync.dao.AccountTreeDao;
import com.lhcy.sync.domain.dto.AccountDto;
import com.lhcy.sync.domain.pojo.Account;
import com.lhcy.sync.web.form.AccountForm;

import java.util.List;

public class AccountService {

    private AccountDao dao = null;
    private AccountTreeDao treeDao = null;

    public AccountService(String dc){
        this.dao = new AccountDao();
        this.treeDao = new AccountTreeDao();
    }

    public int count(AccountForm form) throws Exception {
        return dao.count(form);
    }

    public List<AccountDto> list(int rowBegin, int pageSize, AccountForm form) throws Exception{
        return dao.list(rowBegin, pageSize, form);
    }

    public AccountDto query(String id) throws Exception{
        return dao.query(id);
    }

    public void create(Account vo) throws Exception{
        dao.create(vo);
    }

    public void update(Account vo) throws Exception{
        dao.update(vo);
    }

    public void move(String[] list, String tree, String user) throws Exception{
        dao.move(list, tree, user);
    }
    
    public void delete(String[] list) throws Exception{
        dao.delete(list);
    }

    public void enable(String[] list, int status, String user) throws Exception{
        dao.enable(list, status, user);
    }

    public AccountDto getEquals(String number) throws Exception{
        return dao.getEquals(number);
    }

    public List<TreeNode> treeList() throws Exception{
        return treeDao.list();
    }

    public TreeNode treeQuery(String id) throws Exception{
        return treeDao.query(id);
    }

    public void treeCreate(TreeNode vo) throws Exception{
        treeDao.create(vo);
    }

    public void treeUpdate(TreeNode vo) throws Exception{
        treeDao.update(vo);
    }

    public void treeDelete(String id) throws Exception{
        treeDao.delete(id);
    }

    public boolean treeHasChildren(String id) throws Exception{
        return treeDao.hasChildren(id);
    }

    public TreeNode treeGetEquals(String number) throws Exception{
        return treeDao.getEquals(number);
    }
}
