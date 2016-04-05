package com.wgq.common.service.pool2;

import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by guoweipeng on 16-3-22.
 */
public class ThriftKeyedPoolableObjectFactory<K,V> implements KeyedPoolableObjectFactory<K,V> {

    @Override
    public V makeObject(K key) throws Exception {
        ThriftAddress ta = (ThriftAddress) key ;
        TTransport transport = new TSocket(ta.getIp(), ta.getPost(), 60*1000) ;
        transport.open();
        TProtocol protocol = new TCompactProtocol(transport) ;
        Class clientClass = getClassByName(ta.getServiceClass()) ;
        System.out.println(ta.toString());
        return (V)clientClass.getConstructor(TProtocol.class).newInstance(protocol);
    }

    @Override
    public void destroyObject(K key, V obj) throws Exception {

        if(obj != null) {
            TServiceClient serviceClient = (TServiceClient) obj ;
            try {
                serviceClient.getInputProtocol().getTransport().close();
                System.out.println("关闭正常");
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("关闭异常");
            }
        }
    }

    @Override
    public boolean validateObject(K key, V obj) {
        if(obj != null) {
            TServiceClient serviceClient = (TServiceClient) obj ;
            try{
                return serviceClient.getInputProtocol().getTransport().isOpen() ;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void activateObject(K key, V obj) throws Exception {

    }

    @Override
    public void passivateObject(K key, V obj) throws Exception {

    }

    private Class getClassByName(Class serviceClass) {
        String serviceClassName = serviceClass.getName() ;
        String serviceClientName = serviceClassName.substring(0,serviceClassName.lastIndexOf("$")) + "$Client" ;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader() ;
            Class clientClass = classLoader.loadClass(serviceClientName) ;
            return clientClass ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null ;
    }
}

