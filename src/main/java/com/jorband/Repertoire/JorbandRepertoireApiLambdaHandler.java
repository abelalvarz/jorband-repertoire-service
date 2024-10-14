package com.jorband.Repertoire;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class JorbandRepertoireApiLambdaHandler implements RequestStreamHandler {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try{
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(JorbandRepertoireApplication.class);
        }catch (ContainerInitializationException e){
            log.error("Error Initializing Application, message: {}",e.getMessage());
            throw new RuntimeException("Error when try initialize Spring boot Application",e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        handler.proxyStream(inputStream,outputStream,context);
    }
}
