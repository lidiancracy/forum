package tech.turl.community.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailSenderTest {

    @Autowired
    private emailutil emailSender;

    @Test
    void sendHtmlMail() {
        emailSender.sendHtmlMail("837997288@qq.com", "Test HTML Mail", "This is a test mail.");
    }
}
