package com.m_m.dxpidentityservice.controller;

import com.m_m.dxpidentityservice.model.input.IdentityInput;
import com.m_m.dxpidentityservice.model.output.IdentityResponse;
import com.m_m.dxpidentityservice.service.IdentityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/identity")
@RequiredArgsConstructor
public class IdentityController {

    private final IdentityService identityService;

    @PostMapping
    public ResponseEntity<IdentityResponse> createIdentity(@RequestBody @Valid IdentityInput request) {
         var response = identityService.createIdentity(request);
          return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdentityResponse> getIdentityById(@PathVariable UUID id) {
        var response = identityService.getIdentityById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<IdentityResponse>> getIdentity() {
        var ListResponse = identityService.getIdentities();
        return ResponseEntity.status(HttpStatus.OK).body(ListResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateIdentity(@RequestBody @Valid IdentityInput request, @PathVariable UUID id) {
        identityService.updateIdentity(request, id);
        return ResponseEntity.noContent().build();
    }
}
