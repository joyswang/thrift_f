package com.wgq.common.service.pool;

/**
 * Created by guoweipeng on 16-3-21.
 */
public class Connection{

    private String key ;
    private boolean status ;
    //public Connection() {}

    public Connection(Object key) {
        this.key = String.valueOf(key) ;
    }

    public void connect() {
        this.status = true ;
        System.out.println("connect:连接");
    }

    public void close() {
        this.status = false ;
        System.out.println("close:关闭");
    }

    public boolean isConnect() {
        return this.status ;
    }

    public void sout() {
        System.out.println("当前的key是:-------------" + key);
    }

    public void setKey(String key) {
        this.key = key ;
    }
    public String getKey() {
        return this.key ;
    }
}
