package com.m_m.dxpidentityservice.controller;

import com.m_m.dxpidentityservice.model.input.IdentityInput;
import com.m_m.dxpidentityservice.model.output.IdentityResponse;
import com.m_m.dxpidentityservice.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/identity")
@RequiredArgsConstructor
public class identityController {

    private final IdentityService identityService;

    @PostMapping
    public ResponseEntity<IdentityResponse> createIdentity(IdentityInput request) {
         var response = identityService.createIdentity(request);
          return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
