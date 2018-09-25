//package com.dome.controller;
//
//import com.dome.ftp.FtpClient;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * 测试
// */
//@Controller
//public class TestController {
//
//    @RequestMapping(value = "/test")
//    @ResponseBody
//    public String test(){
//        FtpClient ftpClient=new FtpClient();
//        ftpClient.downFile("192.168.1.81",21,"test","test","/test/","/ftp","D:/log/");
//        return "test";
//    }
//}
