//package com.dome.testfyp;
//
//import org.apache.camel.LoggingLevel;
//import org.apache.camel.builder.RouteBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LocalTransformRoute  extends RouteBuilder {
//
//    private static Logger logger = LoggerFactory.getLogger( LocalTransformRoute.class );
//
//    @Value("${route.parserfile.info}")
//    private String location;
//
//    @Value("${route.parserfile.dir}")
//    private String locationDir;
//
//    @Autowired
//    LocationFileProcessor locationFileProcessor;
//
//    @Override
//    public void configure() throws Exception {
//        from( location )
//                .process( locationFileProcessor )
//                .to( locationDir )
//                .log(LoggingLevel.ERROR, logger, "tirans  file ${file:name} complete.");
//    }
//
//}