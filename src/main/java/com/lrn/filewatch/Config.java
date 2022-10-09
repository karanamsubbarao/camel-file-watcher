package com.lrn.filewatch;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Autowired
    CamelContext context;

    public static final String RECURSIVE = "{{folderWatch.recursive}}";
    public static final String FOLDER= "{{folderWatch.directory}}";
    public static final String POLLING_FREQUENCY= "{{folderWatch.pollingFrequency}}";


}
