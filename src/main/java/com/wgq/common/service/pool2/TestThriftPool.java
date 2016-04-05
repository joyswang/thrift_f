package com.wgq.common.service.pool2;

import com.wgq.thrift.common.service.TestService;
import org.apache.commons.pool.KeyedObjectPool;
import org.apache.thrift.TServiceClient;

/**
 * Created by guoweipeng on 16-3-22.
 */
public class TestThriftPool {
    public static void main(String[] args) {
        KeyedObjectPool<ThriftAddress,TServiceClient> pool = ThriftClientPool.getInstance() ;

        ThriftAddress ta = new ThriftAddress() ;
        ta.setIp("127.0.0.1");
        ta.setPost(7911);
        ta.setServiceClass(TestService.Iface.class);

        try {
            for (int i = 0; i < 10; i++) {
                TestService.Client serviceClient = (TestService.Client) pool.borrowObject(ta);
                try{
                    System.out.println(serviceClient);
                }catch (Exception e) {
                    e.printStackTrace();
                    pool.invalidateObject(ta, serviceClient);
                }finally {
                    pool.returnObject(ta, serviceClient);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pool.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
