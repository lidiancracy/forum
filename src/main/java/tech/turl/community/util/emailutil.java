package tech.turl.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class emailutil {

    private final Logger logger = LoggerFactory.getLogger(emailutil.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${community.email}")
    private String email;

    public void sendHtmlMail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(email);
            helper.setTo(to);
            helper.setSubject(subject);

            String htmlTemplate = Files.readString(Path.of(resourceLoader.getResource("classpath:email-template.html").getURI()));
            String htmlContent = htmlTemplate.replace("{{content}}", content);
            helper.setText(htmlContent, true);

            mailSender.send(message);

            logger.info("A HTML email has been sent from {} to {}.", email, to);
        } catch (MessagingException | IOException e) {
            logger.error("Failed to send email.", e);
        }
    }
}
