package tech.turl.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @ClassName mailtest
 * @Description TODO
 * @Date 2023/7/26 20:25
 */
@SpringBootTest
public class mailtest {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${community.email}")
    private String email;

    @Test
    public void testSendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(email);
        message.setSubject("Test Mail");
        message.setText("This is a test mail.");

        mailSender.send(message);
    }
}
