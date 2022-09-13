package com.temchik.lms.controller.group;

import com.temchik.lms.dto.group.LessonDTO;
import com.temchik.lms.service.group.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/{lessonId}")
    @PreAuthorize("hasAuthority('LESSON:READ')")
    public LessonDTO get(@PathVariable UUID lessonId) {
        return lessonService.get(lessonId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LESSON:READ')")
    public Page<LessonDTO> get(Pageable pageable, @RequestParam UUID courseId) {
        return lessonService.getByCourseId(pageable, courseId);
    }

    @PreAuthorize("hasAuthority('LESSON:WRITE')")
    @PostMapping
    public LessonDTO create(@Validated @RequestBody LessonDTO lessonDIO) {
        return lessonService.create(lessonDIO);
    }
}
