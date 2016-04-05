package com.wgq.common.service.impl;

import com.wgq.thrift.common.bo.User;
import com.wgq.thrift.common.service.TestService;
import org.apache.thrift.TException;

/**
 * Created by guoweipeng on 16-3-16.
 */
public class TestServiceImpl implements TestService.Iface {
    @Override
    public void first_test() throws TException {
        System.out.println("Hello World!");
    }

    @Override
    public boolean checkNull(String str) throws TException {
        if(str == null || "".equals(str)) {
            return true ;
        }
        return false;
    }

    @Override
    public User getUser() throws TException {
        System.out.println("getUser.......");
        User user = new User() ;
        user.setUserName("1111") ;
        user.setUserPass("MMMM") ;
        return user;
    }

    @Override
    public void saveUser(User user) throws TException {
        System.out.println("saveUser.......");

    }
}
