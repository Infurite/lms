package com.temchik.lms.controller.user;

import com.temchik.lms.dto.user.PrivilegeDTO;
import com.temchik.lms.service.user.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/privilege")
@RequiredArgsConstructor
public class PrivilegeController {

    private final PrivilegeService service;

    @GetMapping
    @PreAuthorize("hasAuthority('PRIVILEGE:READ')")
    public Page<PrivilegeDTO> get(Pageable pageable) {
        return service.get(pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PRIVILEGE:WRITE')")
    public PrivilegeDTO create(@Valid @RequestBody PrivilegeDTO dio) {
        return service.create(dio);
    }
}
