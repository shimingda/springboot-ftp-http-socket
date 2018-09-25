//package com.dome.listen;
//
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.io.File;
//
///**
// * 本地文件监控
// */
//@WebListener
//public class ContextListener implements ServletContextListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent arg0) {
//        final File file = new File("D:\\logs");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    new WatchDir(file, true, new FileActionCallback() {
//                        @Override
//                        public void create(File file) {
//                            System.out.println("文件已创建\t" + file.getAbsolutePath());
//                        }
//                        @Override
//                        public void delete(File file) {
//                            System.out.println("文件已删除\t" + file.getAbsolutePath());
//                        }
//                        @Override
//                        public void modify(File file) {
//                            System.out.println("文件已修改\t" + file.getAbsolutePath());
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        System.out.println("正在监视文件夹:" + file.getAbsolutePath());
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }
//}
