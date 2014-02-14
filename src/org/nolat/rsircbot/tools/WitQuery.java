package org.nolat.rsircbot.tools;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.nolat.rsircbot.tools.json.WitResponse;
import org.nolat.rsircbot.tools.json.WitResponse.Entity;

import com.google.gson.Gson;

public class WitQuery {

    private final String WIT_URL = "https://api.wit.ai/message?q=";
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String BEARER_FORMAT = "Bearer %s";
    private final String witToken;
    private final String query;

    private final String rawOutput;
    private final WitResponse witResponse;

    public WitQuery(String query, String witToken) {
        this.witToken = witToken;
        this.query = query;
        if (query.length() == 0 || query.length() > 256) {
            throw new IllegalArgumentException(
                    "Query must be at least 1 character but no more than 256 characters long.");
        }
        rawOutput = fetchResult();
        Gson gson = new Gson();
        witResponse = gson.fromJson(rawOutput, WitResponse.class);
    }

    private String fetchResult() {
        String response = null;
        try {
            System.out.println("Wit: Requesting .... " + query);
            final String getUrl = String.format("%s%s", WIT_URL, URLEncoder.encode(query, "utf-8"));
            URL url = new URL(getUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.addRequestProperty(AUTHORIZATION_HEADER, String.format(BEARER_FORMAT, witToken));
            try {
                final InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                response = IOUtils.toString(in);
                in.close();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            System.err.println("An error occurred during the request: " + e.getMessage());
        }
        return response;
    }

    public String getRawOutput() {
        return rawOutput;
    }

    public String getAnswer() {
        String answer = "";

        answer += "Intent: " + witResponse.getOutcome().getIntent();
        for (Map.Entry<String, Entity> entity : witResponse.getOutcome().getEntities().entrySet()) {
            answer += "; " + entity.getKey() + ":" + entity.getValue();
        }
        answer += " (Conf: " + witResponse.getOutcome().getConfidence() + ")";
        return answer;
    }
}
