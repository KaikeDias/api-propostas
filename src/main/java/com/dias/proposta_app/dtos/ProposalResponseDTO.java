package com.dias.proposta_app.dtos;

import com.dias.proposta_app.entities.Proposal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProposalResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private String cpf;
    private String phone;
    private Double income;
    private Double requestedValue;
    private int paymentTerm;
    private Boolean approved;
    private String observation;

    public static ProposalResponseDTO fromEntity(Proposal proposal) {
        return ProposalResponseDTO.builder()
                .id(proposal.getId())
                .name(proposal.getUser().getName())
                .lastName(proposal.getUser().getLastName())
                .cpf(proposal.getUser().getCpf())
                .phone(proposal.getUser().getPhone())
                .income(proposal.getUser().getIncome())
                .requestedValue(proposal.getRequestedValue())
                .approved(proposal.getApproved())
                .observation(proposal.getObservation())
                .build();
    }
}
