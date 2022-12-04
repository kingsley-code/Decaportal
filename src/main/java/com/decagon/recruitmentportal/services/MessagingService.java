package com.decagon.recruitmentportal.services;
import com.decagon.recruitmentportal.pojos.APIResponse;
import com.decagon.recruitmentportal.pojos.GmailDTO;
import com.decagon.recruitmentportal.repositories.ApplicantRepository;
import com.decagon.recruitmentportal.util.App;
import com.decagon.recruitmentportal.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MessagingService {

    @Autowired
    private final App app;
    @Autowired
    private final Response response;
    @Autowired
    private RestTemplate rest;
    private final ApplicantRepository applicantRepository;
    @Value("bulksmsnigeria.baseURL")
    private String smsBaseURL;
    @Value("bulksmsnigeria.smsToken")
    private String smsToken;

//    @Value("whatsapp.accessKey")
    private String whatsappAccessKey="Bearer EAAKh4CBRdIIBAP9lVEtOhb7uUs0oFhLuFSs5BvVEfdqzM29eZBWsrC4HL5ahh7YBuiZAr3txYUZA2EdZCUg0klVkNvkblK6UvAPaxjb4vdT9r9gs8gUNA7ZC65h5EdZCXrtxLjtLgWKZC9VDZBpycVfydkD3iU98eR7iOnQmZBEr7cthKgffaa1xz";
//    @Value("whatsapp.baseURL")
    private String whatsappBaseURL="https://graph.facebook.com/v14.0/101883642540294/messages";


    @Autowired
    @Qualifier("gmail")
    private JavaMailSender mailSender;
    private final LocalStorageService memcached;


    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setCacheControl("no-cache");
        return headers;
    }


    public APIResponse sendWhatsappMessage(String recipient, String textMessage) {
        recipient=app.getFormattedNumber(recipient).substring(1);
        app.print(""+recipient);
        app.print(""+textMessage);
        app.print("Whatsapp Message Sending...");
        try {

            String requestBody="{\"messaging_product\":\"whatsapp\",\"to\":\""+recipient+"\",\"type\":\"template\",\"template\":{\"name\":\"dispatch_buddy_access_token\",\"language\":{\"code\":\"en_US\"},\"components\":[{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\""+textMessage+"\"}]}]}}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization",whatsappAccessKey);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = rest.exchange(whatsappBaseURL, HttpMethod.POST, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                app.print("Message Sent");
                return new APIResponse("Message Sent", true, response.getBody());
            } else {
                app.print("Failed");
                return new APIResponse("Failed to send Message", false, response.getBody());
            }
        }catch (Exception ex){
            app.print("Unable to send Message");
            ex.printStackTrace();
            return new APIResponse(ex.getMessage(), false, null);
        }
    }

    public APIResponse sendMail(GmailDTO gmailDTO) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(gmailDTO.getToAddresses());
            message.setFrom("dispatchbuddy@gmail.com");
            message.setSubject(gmailDTO.getSubject());
            message.setText(gmailDTO.getBody(), true);
        };

        try {
            mailSender.send(preparator);
            String sent = "Email sent successfully To "+gmailDTO.getToAddresses();
            return  response.success(sent);
        } catch (MailException e) {
            return response.failure("Mail not send because: "+e.getMessage());
        }
    }
}
