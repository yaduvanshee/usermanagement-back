package com.userManagement.userManagement.mailer;

import com.userManagement.userManagement.constants.MessageConstant;
import com.userManagement.userManagement.errorEnum.AddressErrorEnum;
import com.userManagement.userManagement.errorEnum.UserErrorEnum;
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
        try {
            emailSender.sendEmail(recipientEmail, MessageConstant.WELCOME_SUBJECT, MessageConstant.WELCOME_CONTENT);
        } catch (MessagingException e) {
            UserErrorEnum error = UserErrorEnum.WELCOME_EMAIL_ERROR;
            ErrorResponse errorResponse = new ErrorResponse(error.getErrorMsg(),error.getErrorCode(), false);
            throw new EmailSendingException(errorResponse);
        } catch (UnsupportedEncodingException e) {
            UserErrorEnum error = UserErrorEnum.UNSUPPORTED_ENCODING_ERROR;
            ErrorResponse errorResponse = new ErrorResponse(error.getErrorMsg(),error.getErrorCode(), false);
            throw new UserManagementException(errorResponse);
        }
    }

    @SneakyThrows
    public void sendAddressChange(String recipientEmail) {
        try {
            emailSender.sendEmail(recipientEmail, MessageConstant.USER_ADDRESS_CHANGE_SUBJECT, MessageConstant.USER_ADDRESS_CHANGE_CONTENT);
        } catch (MessagingException e) {
            AddressErrorEnum error = AddressErrorEnum.ADDRESS_EMAIL_ERROR;
            ErrorResponse errorResponse = new ErrorResponse(error.getErrorMsg(),error.getErrorCode(), false);
            throw new EmailSendingException(errorResponse);
        } catch (UnsupportedEncodingException e) {
            UserErrorEnum error = UserErrorEnum.UNSUPPORTED_ENCODING_ERROR;
            ErrorResponse errorResponse = new ErrorResponse(error.getErrorMsg(),error.getErrorCode(), false);
            throw new UserManagementException(errorResponse);
        }
    }
}
