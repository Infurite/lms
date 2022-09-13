package com.temchik.lms.controller.user;

import com.temchik.lms.dto.user.UserDTO;
import com.temchik.lms.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER:READ')")
    public UserDTO get(Principal principal) {
        return userService.get(UUID.fromString(principal.getName()));
    }

    @PostMapping("/register")
    public UserDTO create(@Valid @RequestBody UserDTO userDIO) {
        return userService.create(userDIO);
    }

    @PostMapping("/confirm/{token}")
    public UserDTO confirmUser(@PathVariable String token) {
        return userService.confirmUser(token);
    }
}
