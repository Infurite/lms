package com.temchik.lms.controller.user;

import com.temchik.lms.dto.user.AccountDTO;
import com.temchik.lms.service.user.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/user/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    @PreAuthorize("hasAuthority('ACCOUNT:READ')")
    public AccountDTO getAccount(Principal principal) {
        return accountService.get(UUID.fromString(principal.getName()));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ACCOUNT:WRITE')")
    public AccountDTO createAccount(@Valid @RequestBody AccountDTO accountDTO, Principal principal) {
        return accountService.create(UUID.fromString(principal.getName()), accountDTO);
    }
}
