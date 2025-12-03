package com.dias.proposta_app.services;

import com.dias.proposta_app.dtos.ProposalRequestDTO;
import com.dias.proposta_app.dtos.ProposalResponseDTO;
import com.dias.proposta_app.entities.Proposal;
import com.dias.proposta_app.repositories.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;

    public ProposalResponseDTO create(ProposalRequestDTO dto) {
        Proposal proposal = ProposalRequestDTO.mapToEntity(dto);

        Proposal savedProposal = proposalRepository.save(proposal);

        return ProposalResponseDTO.fromEntity(savedProposal);
    }

}
