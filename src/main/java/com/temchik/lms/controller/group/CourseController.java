package com.temchik.lms.controller.group;

import com.temchik.lms.dto.group.CourseDTO;
import com.temchik.lms.service.group.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @PreAuthorize("hasAuthority('COURSE:READ')")
    public Page<CourseDTO> get(Pageable pageable, @RequestParam UUID groupId) {
        return courseService.getByGroupId(pageable, groupId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('COURSE:WRITE')")
    public CourseDTO create(@Validated @RequestBody CourseDTO courseDIO) {
        return courseService.create(courseDIO);
    }
}
