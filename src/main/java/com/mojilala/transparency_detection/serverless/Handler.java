package com.mojilala.transparency_detection.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mojilala.transparency_detection.DetectionResult;
import com.mojilala.transparency_detection.TransparencyAnalyzer;
import org.apache.log4j.Logger;

import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, Object> {

    private static final Logger LOG = Logger.getLogger(Handler.class);

    public Object handleRequest(Map<String, Object> input, Context context) {
        LOG.info("received: " + input);


        Map<String, Object> query = (Map<String, Object>) input.get("query");

        if (!query.containsKey("url"))
            return new ErrorResponse("url query parameter cannot be blank!");

        TransparencyAnalyzer recognizer = new TransparencyAnalyzer();

        String url = (String) query.get("url");

        url = url.replaceAll(" ", "+");

        DetectionResult res;
        try {
            res = recognizer.analyze(url);
        } catch (Exception e) {
            LOG.error("An error occured: " + e.getMessage());
            return new ErrorResponse("An error occured: " + e);
        }

        return res;
    }
}
