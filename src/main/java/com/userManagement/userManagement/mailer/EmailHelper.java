package com.userManagement.userManagement.mailer;

import com.userManagement.userManagement.exception.EmailSendingException;
import com.userManagement.userManagement.exception.UserManagementException;
import com.userManagement.userManagement.response.ErrorResponse;
import jakarta.mail.MessagingException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class EmailHelper {
    private EmailSender emailSender;

    public EmailHelper(EmailSender emailSender){
        this.emailSender = emailSender;
    }

    @SneakyThrows
    public void sendWelcomeMail(String recipientEmail) {
        String subject = "Welcome to our application!";
        String content = "Dear User,\n\nWelcome to our application!\n\nBest regards,\nYour Application Team";

        try {
            emailSender.sendEmail(recipientEmail, subject, content);
        } catch (MessagingException e) {
            ErrorResponse errorResponse = new ErrorResponse("EMAIL_SENDING_ERROR", "Error occurred while sending email.");
            throw new EmailSendingException(errorResponse);
        } catch (UnsupportedEncodingException e) {
            ErrorResponse errorResponse = new ErrorResponse("UNSUPPORTED_ENCODING_ERROR", "Unsupported encoding detected.");
            throw new UserManagementException(errorResponse);
        }
    }
}
