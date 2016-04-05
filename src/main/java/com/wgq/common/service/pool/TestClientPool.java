package com.wgq.common.service.pool;

import org.apache.commons.pool.KeyedObjectPool;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;

/**
 * Created by guoweipeng on 16-3-21.
 */
public class TestClientPool {

    private KeyedObjectPool clientpool ;

    public void initPool() {
        GenericKeyedObjectPool.Config config = new GenericKeyedObjectPool.Config() ;
        config.lifo = true ;    //last in first out
        config.maxActive = 5 ; //最大连接数
        config.maxWait = 10 * 1000 ;
        config.maxIdle = 2 ;   //最大空闲连接数
        config.testOnBorrow = true;
        config.testWhileIdle = true;
        config.timeBetweenEvictionRunsMillis = 3 * 1000;

        MyKeyedPoolableObjectFactory factory = new MyKeyedPoolableObjectFactory() ;
        clientpool = new GenericKeyedObjectPool(factory,config) ;
    }

    public KeyedObjectPool getClientpool() {
        return this.clientpool ;
    }

    public static void main(String[] args) {
        TestClientPool pool = new TestClientPool() ;
        pool.initPool();
        KeyedObjectPool clientpool = pool.getClientpool() ;

        try{
            for(int i=0;i<10;i++) {
                String key = "count_xxx" ;
                Connection conn = (Connection)clientpool.borrowObject(key) ;
                System.out.println(conn);
                try{
                    conn.sout();
                }catch (Exception e) {
                    clientpool.invalidateObject(key,conn);
                }finally {
                    clientpool.returnObject(key, conn);         //还回对象
                }

            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                clientpool.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
