package ru.se.web.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGridAPI;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Service
public class EmailService {
    private final JavaMailSender sender;
    private final String activateLink="https://cos41015803000040.herokuapp.com/activate/";
    private final SendGridAPI sendGrid;
    private static final Logger LOGGER= LoggerFactory.getLogger(EmailService.class);
    @Autowired
    public EmailService(JavaMailSender sender,SendGridAPI sendGrid) {
        this.sender = sender;
        this.sendGrid=sendGrid;
    }
    public void  sendActivationEmail(String email){
        Mail mail=new Mail();
        mail.setFrom(new Email("5803000040@rumail.ru.ac.th","APFreshfood"));
        mail.setTemplateId("d-45c2b2853ee74620ac2d4e2448de16ab");
        Personalization persona=new Personalization();
        persona.addDynamicTemplateData("link",activateLink+encode(email));
        persona.addTo(new Email(email));
        mail.addPersonalization(persona);
        try{
            Request req=new Request();
            req.setMethod(Method.POST);
            req.setEndpoint("mail/send");
            req.setBody(mail.build());
            Response res=sendGrid.api(req);
            LOGGER.info(res.getStatusCode()+" :code");
            LOGGER.info(res.getBody()+" :body");
            LOGGER.info(res.getHeaders()+" :header");
        }catch(Exception ex){
            LOGGER.info(ex.toString());
        }
    }
    /*public void sendActivationEmail(String email){

        try{
            SimpleMailMessage message=new SimpleMailMessage();
            String url=activateLink+encode(email);
            message.setTo(email);
            message.setSubject("กรุณายืนยันการสมัครสมาชิก");
            message.setText(url);
            sender.send(message);
        }catch(Exception ex){
            LOGGER.info(ex.toString());
        }
    }*/

    public String encode(String text){
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
    public String decode(String text){
        return new String(Base64.getDecoder().decode(text.getBytes()));
    }
}
