package com.dome.testfyp;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {
    private static Logger logger = LoggerFactory.getLogger( MyRoute.class );
    @Value("${ftp.server.info}")
    private String url;
    @Value("${ftp.local.dir}")
    private String downloadLocation;
    @Override
    public void configure() throws Exception {
        from(url)
                .to(downloadLocation)
                .log(LoggingLevel.ERROR, logger, "Downloaded file ${file:name} complete.");
    }
}
