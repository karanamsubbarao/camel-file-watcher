package com.lrn.filewatch;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class FileProcessor implements Processor {

    private static final Logger LOGGER= LoggerFactory.getLogger(FileProcessor.class);


    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = exchange.getIn();
        Map<String, Object> headers = message.getHeaders();
        for (Map.Entry<String, Object> entry:headers.entrySet())
        {
            LOGGER.info("Key..."  + entry.getKey()  + " Value .. " + entry.getValue().toString());
        }

    }
}
