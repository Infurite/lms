package com.temchik.lms.service.group;

import com.temchik.lms.common.exception.BusinessEntityNotFoundException;
import com.temchik.lms.common.exception.BusinessException;
import com.temchik.lms.dto.group.LessonDTO;
import com.temchik.lms.model.group.Lesson;
import com.temchik.lms.repository.group.LessonRepository;
import com.temchik.lms.service.mapper.group.LessonDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository repository;
    private final LessonDTOMapper mapper;

    @Transactional(readOnly = true)
    public Lesson findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessEntityNotFoundException(Lesson.class, "id"));
    }

    public LessonDTO get(UUID id) {
        return mapper.toDTO(findById(id));
    }

    public Page<LessonDTO> getByCourseId(Pageable pageable, UUID courseId) {
        return repository.findByCourseId(pageable, courseId).map(mapper::toDTO);
    }

    public LessonDTO create(LessonDTO dio) {
        checkUnique(dio.getCourseId(), dio.getName());

        Lesson lesson = mapper.toEntity(dio);
        return mapper.toDTO(repository.save(lesson));
    }

    public void checkUnique(UUID courseId, String name) {
        if (repository.existsByCourseIdAndNameIgnoreCase(courseId, name)) {
            throw new BusinessException("CourseId or Name already exists");
        }
    }
}
