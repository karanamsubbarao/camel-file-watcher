package com.lrn.filewatch;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FolderWatchRouter extends RouteBuilder {

    @Autowired
    AppConfig config;

    @Override
    public void configure() throws Exception {

        from("file-watch://" + config.getDirectory())
                .process(new FileProcessor())
                .log("FILE WATCH: ${header." + Exchange.FILE_NAME + "}");
    }
}
