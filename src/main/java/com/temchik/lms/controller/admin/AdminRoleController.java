package com.temchik.lms.controller.admin;

import com.temchik.lms.dto.user.AdminRoleDTO;
import com.temchik.lms.service.user.AdminRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/role")
@RequiredArgsConstructor
public class AdminRoleController {

    private final AdminRoleService adminRoleService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE:READ')")
    public List<AdminRoleDTO> get() {
        return adminRoleService.get();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE:WRITE')")
    public AdminRoleDTO create(@Validated @RequestBody AdminRoleDTO adminRoleDIO) {
        return adminRoleService.create(adminRoleDIO);
    }

    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('ROLE:WRITE')")
    public void delete(@PathVariable UUID roleId) {
        adminRoleService.delete(roleId);
    }

}
