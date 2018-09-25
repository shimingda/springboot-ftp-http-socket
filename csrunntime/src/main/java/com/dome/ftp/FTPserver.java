//package com.dome.ftp;
//
//import org.apache.ftpserver.FtpServer;
//import org.apache.ftpserver.FtpServerFactory;
//import org.apache.ftpserver.ftplet.FtpException;
//import org.apache.ftpserver.ftplet.Ftplet;
//import org.apache.ftpserver.listener.ListenerFactory;
//import org.apache.ftpserver.usermanager.impl.BaseUser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.util.LinkedHashMap;
//
//public class FTPserver {
//    private static final Logger LOGGER = LoggerFactory.getLogger(FTPserver.class);
//
//        @Autowired
//        private FtpLetCallBackListen ftpLetCallBackListen;
//
//        @SuppressWarnings("resource")
//        @PostConstruct
//        public void init () throws FtpException, IOException {
//
//            FtpServerFactory serverFactory = new FtpServerFactory();
//            ListenerFactory listenerFactory = new ListenerFactory();
//
//            listenerFactory.setPort(2121);
//            serverFactory.addListener("default", listenerFactory.createListener());
//            //用户名
//            BaseUser user = new BaseUser();
//            user.setName("test");
//            //密码 如果不设置密码就是匿名用户
//            user.setPassword("test");
//            FtpServer server = serverFactory.createServer();
//            // set upload callback
//            LinkedHashMap<String, Ftplet> ftplets = new LinkedHashMap<>();
//            ftplets.put("uploadEnd", ftpLetCallBackListen);
//            serverFactory.setFtplets(ftplets);
//
//            server.start();
//
//        }
//
//}
