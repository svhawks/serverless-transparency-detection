package com.mojilala.transparency_detection.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mojilala.transparency_detection.TransparencyAnalyzer;
import org.apache.log4j.Logger;

import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, String> {

	private static final Logger LOG = Logger.getLogger(Handler.class);

	public String handleRequest(Map<String, Object> input, Context context) {
        LOG.info("received: " + input);


        Map<String, Object> query = (Map<String, Object>) input.get("query");

        if (!query.containsKey("url"))
            return new ErrorResponse("url query parameter cannot be blank!").toJson();

        TransparencyAnalyzer recognizer = new TransparencyAnalyzer();

        String url = (String) query.get("url");

        url = url.replaceAll(" ", "+");

        String res;
        try {
            res = recognizer.analyze(url);
        } catch (Exception e) {
            LOG.error("An error occured: " + e.getMessage());
            res = new ErrorResponse("An error occured: " + e).toJson();
        }

        return res;
    }
}
