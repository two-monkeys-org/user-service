package org.monke.userservice.service;

import org.monke.userservice.entity.Mail;

public interface EmailService {
    void send(Mail mail);
}
