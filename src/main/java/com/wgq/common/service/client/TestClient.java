package com.wgq.common.service.client;

import com.wgq.thrift.common.bo.User;
import com.wgq.thrift.common.service.TestService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by guoweipeng on 16-3-16.
 */
public class TestClient {

    public static void main(String[] args) {
        TTransport transport = null ;
        try{
            System.out.println("进入......");
            transport = new TSocket("local.op.com", 7911) ;
            transport.open();

            TProtocol protocol = new TCompactProtocol(transport);
            TestService.Client client = new TestService.Client(protocol) ;

            long start = System.currentTimeMillis() ;
            client.first_test();
            boolean bool = client.checkNull("") ;
            System.out.println(String.valueOf(bool));
            System.out.println(System.currentTimeMillis() - start);




            User user = client.getUser() ;
            client.saveUser(user);


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            transport.close();
        }
    }
}
