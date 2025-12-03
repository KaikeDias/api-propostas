package com.dias.proposta_app.dtos;

import com.dias.proposta_app.entities.Proposal;
import com.dias.proposta_app.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProposalRequestDTO {
    private String name;
    private String lastName;
    private String cpf;
    private String phone;
    private Double income;
    private Double requestedValue;
    private int paymentTerm;

    public static Proposal mapToEntity(ProposalRequestDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .lastName(dto.getLastName())
                .cpf(dto.getCpf())
                .phone(dto.getPhone())
                .income(dto.getIncome())
                .build();

        return Proposal.builder()
                .requestedValue(dto.getRequestedValue())
                .paymentTerm(dto.getPaymentTerm())
                .user(user)
                .build();
    }
}
