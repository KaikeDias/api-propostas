package com.dias.proposta_app.services;

import com.dias.proposta_app.dtos.ProposalRequestDTO;
import com.dias.proposta_app.dtos.ProposalResponseDTO;
import com.dias.proposta_app.entities.Proposal;
import com.dias.proposta_app.repositories.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final NotificationService notificationService;

    @Value("${rabbitmq.pending-proposal.exchange}")
    private String exchange;

    public ProposalResponseDTO create(ProposalRequestDTO dto) {
        Proposal proposal = ProposalRequestDTO.mapToEntity(dto);

        Proposal savedProposal = proposalRepository.save(proposal);
        sendNotificationRabbitMQ(savedProposal);

        return ProposalResponseDTO.fromEntity(savedProposal);
    }

    private void sendNotificationRabbitMQ(Proposal proposal) {
        try {
            notificationService.sendNotification(proposal, exchange);
        } catch (RuntimeException e) {
            proposal.setIntegrated(false);
            proposalRepository.save(proposal);
        }
    }

}
