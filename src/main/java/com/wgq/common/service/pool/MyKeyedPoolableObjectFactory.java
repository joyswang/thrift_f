package com.wgq.common.service.pool;

import org.apache.commons.pool.KeyedPoolableObjectFactory;

/**
 * Created by guoweipeng on 16-3-21.
 */
public class MyKeyedPoolableObjectFactory implements KeyedPoolableObjectFactory {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object makeObject(Object key) throws Exception {
        Connection conn = new Connection(key) ;
        //System.out.println("make");
        //return (T)Connection.class.getConstructor(Object.class).newInstance(key.toString());
        conn.connect();
        return conn ;
    }

    @Override
    public void destroyObject(Object key,Object obj) throws Exception {
        Connection conn = (Connection) obj ;
        conn.close();
        System.out.println("关闭线程池");
    }

    @Override
    public boolean validateObject(Object key, Object obj) {
        Connection conn = (Connection) obj ;
        return conn.isConnect();
    }

    @Override
    public void activateObject(Object key, Object obj) throws Exception {

    }

    @Override
    public void passivateObject(Object key, Object obj) throws Exception {

    }
}
