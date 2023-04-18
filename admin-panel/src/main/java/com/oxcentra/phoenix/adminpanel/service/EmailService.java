package com.oxcentra.phoenix.adminpanel.service;

public interface EmailService {
    public Boolean sendEmail(String toEmail,
                             String body,
                             String subject);
}
