package com.dias.proposta_app.services;

import com.dias.proposta_app.dtos.ProposalResponseDTO;
import com.dias.proposta_app.entities.Proposal;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final RabbitTemplate rabbitTemplate;

    public void sendNotification(Proposal proposal, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposal);
    }
}
