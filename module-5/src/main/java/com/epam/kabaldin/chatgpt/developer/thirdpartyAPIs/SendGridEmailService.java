package com.epam.kabaldin.chatgpt.developer.thirdpartyAPIs;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import java.io.IOException;

public class SendGridEmailService {
    private static final String SENDGRID_API_KEY = "your_sendgrid_api_key";

    public static void main(String[] args) {
        Email from = new Email("your_email@example.com");
        String subject = "Hello World from SendGrid!";
        Email to = new Email("recipient@example.com");
        Content content = new Content("text/plain", "This is the plain text content of the email.");
        Mail mail = new Mail(from, subject, to, content);

        // Set the SendGrid API key
        SendGrid sg = new SendGrid(SENDGRID_API_KEY);

        // Enable template ID
        mail.setTemplateId("your_template_id");

        // Add template substitution values
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("name", "John Doe");
        personalization.addDynamicTemplateData("product", "My Product");
        mail.addPersonalization(personalization);

        // Send the email
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

