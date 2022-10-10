package com.lrn.filewatch;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.transfer.s3.FileUpload;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.UploadFileRequest;

import java.nio.file.Paths;
import java.util.Map;

import static software.amazon.awssdk.transfer.s3.SizeConstant.MB;

@Component
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
