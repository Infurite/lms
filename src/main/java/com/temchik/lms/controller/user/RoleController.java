package com.temchik.lms.controller.user;

import com.temchik.lms.dto.user.RoleDTO;
import com.temchik.lms.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE:READ')")
    public Page<RoleDTO> get(Pageable pageable) {
        return roleService.getAvailableRoles(pageable);
    }
}
