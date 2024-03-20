package uz.oauth2.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import uz.oauth2.oauth2.Service.EmailService;

@SpringBootApplication
public class Oauth2Application {


    public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}
}
