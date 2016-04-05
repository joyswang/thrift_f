package com.wgq.common.service.server;

import com.wgq.common.service.impl.TestServiceImpl;
import com.wgq.thrift.common.service.TestService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created by guoweipeng on 16-3-16.
 */
public class TestServer {
    public static void main(String[] args) {
        try{
            TServerTransport serverTransport = new TServerSocket(7911) ;
            TProtocolFactory proFactory = new TCompactProtocol.Factory() ;
            TProcessor processor = new TestService.Processor(new TestServiceImpl());
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport)
                    .protocolFactory(proFactory)
                    .processor(processor));
            System.out.println("Start server on port 7911...");
            server.serve();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
