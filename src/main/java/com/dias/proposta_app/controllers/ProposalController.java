package com.dias.proposta_app.controllers;

import com.dias.proposta_app.dtos.ProposalRequestDTO;
import com.dias.proposta_app.dtos.ProposalResponseDTO;
import com.dias.proposta_app.services.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposals")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    @PostMapping
    public ResponseEntity<ProposalResponseDTO> create(@RequestBody ProposalRequestDTO dto) {
        ProposalResponseDTO response = proposalService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
