package com.wgq.common.service.pool2;

import org.apache.commons.pool.KeyedObjectPool;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.apache.thrift.TServiceClient;

/**
 * Created by guoweipeng on 16-3-22.
 */
public class ThriftClientPool {
    private final static KeyedObjectPool<ThriftAddress,TServiceClient> pool ;

    static {
        GenericKeyedObjectPool.Config config = new GenericKeyedObjectPool.Config() ;
        config.lifo = true ;    //last in first out
        config.maxActive = 5 ; //最大连接数
        config.maxWait = 10 * 1000 ;
        config.maxIdle = 2 ;   //最大空闲连接数
        config.testOnBorrow = true;
        config.testWhileIdle = true;
        config.timeBetweenEvictionRunsMillis = 3 * 1000;

        ThriftKeyedPoolableObjectFactory<ThriftAddress,TServiceClient> factory = new ThriftKeyedPoolableObjectFactory<ThriftAddress,TServiceClient>() ;

        pool = new GenericKeyedObjectPool<ThriftAddress,TServiceClient>(factory,config) ;
    }

    private ThriftClientPool(){}

    public static KeyedObjectPool<ThriftAddress,TServiceClient> getInstance() {
        return pool ;
    }

}
