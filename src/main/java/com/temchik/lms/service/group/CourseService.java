package com.temchik.lms.service.group;

import com.temchik.lms.common.exception.BusinessException;
import com.temchik.lms.dto.group.CourseDTO;
import com.temchik.lms.model.group.Course;
import com.temchik.lms.repository.group.CourseRepository;
import com.temchik.lms.service.mapper.group.CourseDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CourseDTOMapper mapper;

    public Page<CourseDTO> getByGroupId(Pageable pageable, UUID groupId) {
        return repository.findByGroupId(pageable, groupId).map(mapper::toDTO);
    }

    @Transactional
    public CourseDTO create(CourseDTO dio) {
        checkUnique(dio.getGroupId(), dio.getName());

        Course course = mapper.toEntity(dio);
        return mapper.toDTO(repository.save(course));
    }

    private void checkUnique(UUID groupId, String name) {
        if (repository.existsByGroupIdAndNameIgnoreCase(groupId, name)) {
            throw new BusinessException("GroupId or Name already exists");
        }
    }
}


