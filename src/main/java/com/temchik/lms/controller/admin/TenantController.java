package com.temchik.lms.controller.admin;

import com.temchik.lms.dto.TenantDTO;
import com.temchik.lms.service.user.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/tenant")
public class TenantController {

    private final TenantService tenantService;

    @GetMapping
    @PreAuthorize("hasAuthority('TENANT:READ')")
    public Page<TenantDTO> get(Pageable pageable) {
        return tenantService.get(pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('TENANT:WRITE')")
    public TenantDTO create(@Validated @RequestBody TenantDTO tenantDIO) {
        return tenantService.create(tenantDIO);
    }

    @DeleteMapping("/{tenantId}")
    @PreAuthorize("hasAuthority('TENANT:WRITE')")
    public void delete(@PathVariable UUID tenantId) {
        tenantService.delete(tenantId);
    }
}
