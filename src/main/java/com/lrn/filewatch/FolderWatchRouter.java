package com.lrn.filewatch;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.camel.component.aws2.s3.AWS2S3Constants;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;

@Component
public class FolderWatchRouter extends RouteBuilder {

    @Autowired
    AppConfig config;

    @Autowired
    private FileProcessor fileProcessor;

    @Autowired
    private S3Client amazonS3Client;

    @Override
    public void configure() throws Exception {
        from("file-watch://" + config.getDirectory())
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setHeader(AWS2S3Constants.KEY, exchange.getProperty(Exchange.FILE_PATH));
                    }
                })
                .setHeader(AWS2S3Constants.CONTENT_LENGTH, simple("${in.header.CamelFileLength}"))
                .setHeader(AWS2S3Constants.KEY,simple("${in.header.CamelFileNameOnly}"))
                .to("aws2-s3://{{awsS3BucketName}}?accessKey={{awsAccessKey}}&secretKey=RAW({{awsAccessKeySecret}})")
                .log("done.");
    }

}
