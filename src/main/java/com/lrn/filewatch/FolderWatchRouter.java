package com.lrn.filewatch;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FolderWatchRouter extends RouteBuilder {

    @Autowired
    Config config;

    @Override
    public void configure() throws Exception {

        String recursive = "recursive=" + Config.RECURSIVE;
        String regex = "antInclude=**/*.moveme";
        String watchEvents = "events=CREATE,MODIFY";

        from("file-watch://" + Config.FOLDER)
                .process(new FileProcessor()) // essentially calls the process() method in the FileProcessor class
                .log("FILE WATCH: ${header." + Exchange.FILE_NAME + "}");
    }
}
