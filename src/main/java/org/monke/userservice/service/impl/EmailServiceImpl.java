package org.monke.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.monke.userservice.entity.Mail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.monke.userservice.service.EmailService;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final RestTemplate restTemplate;

    @Override
    public void send(Mail mail) {
        // TODO add check for response code :)
        ResponseEntity<?> response = restTemplate.postForEntity("http://email-service/email-service/send", mail, Object.class);
    }
}
