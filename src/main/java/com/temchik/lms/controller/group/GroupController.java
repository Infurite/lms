package com.temchik.lms.controller.group;

import com.temchik.lms.dto.group.GroupDTO;
import com.temchik.lms.service.group.GroupService;
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
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    @PreAuthorize("hasAuthority('GROUP:READ')")
    public Page<GroupDTO> get(Pageable pageable) {
        return groupService.get(pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('GROUP:WRITE')")
    public GroupDTO create(@Validated @RequestBody GroupDTO groupDIO) {
        return groupService.create(groupDIO);
    }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasAuthority('GROUP:WRITE')")
    public void delete(@PathVariable UUID groupId) {
        groupService.delete(groupId);
    }
}
