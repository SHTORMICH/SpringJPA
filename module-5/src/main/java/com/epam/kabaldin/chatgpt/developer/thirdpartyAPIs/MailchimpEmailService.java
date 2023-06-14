package com.epam.kabaldin.chatgpt.developer.thirdpartyAPIs;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;

import java.io.IOException;

public class MailchimpEmailService {
    private static final String MAILCHIMP_API_KEY = "your_mailchimp_api_key";
    private static final String MAILCHIMP_SERVER_PREFIX = "your_mailchimp_server_prefix";
    private static final String MAILCHIMP_LIST_ID = "your_mailchimp_list_id";

    public static void main(String[] args) {
        String apiKey = "apikey:" + MAILCHIMP_API_KEY;
        String url = "https://" + MAILCHIMP_SERVER_PREFIX + ".api.mailchimp.com/3.0/lists/" + MAILCHIMP_LIST_ID + "/members";

        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        try {
            HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(url), null);
            request.getHeaders().setAuthorization(apiKey);
            request.getHeaders().setContentType("application/json");

            MailchimpEmail email = new MailchimpEmail("subscriber@example.com", "John Doe");

            String payload = new Gson().toJson(email);
            request.setContent(new ByteArrayContent(null, payload.getBytes()));

            HttpResponse response = request.execute();
            System.out.println(response.getStatusCode());
            System.out.println(response.getStatusMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class MailchimpEmail {
        private String email_address;
        private String name;

        public MailchimpEmail(String email_address, String name) {
            this.email_address = email_address;
            this.name = name;
        }
    }
}
