package com.temchik.lms.service.mapper.group;

import com.temchik.lms.dto.group.CourseDTO;
import com.temchik.lms.model.group.Course;
import com.temchik.lms.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CourseDTOMapper implements EntityToDTOMapper<CourseDTO, Course, CourseDTO> {

    private final ModelMapper mapper = new ModelMapper();

    public CourseDTOMapper() {
        mapper.addMappings(new PropertyMap<CourseDTO, Course>() {
            protected void configure() {
                skip().setId(null);
            }
        });
    }

    @Override
    public CourseDTO toDTO(Course entity, Object... args) {
        return mapper.map(entity, CourseDTO.class);
    }

    @Override
    public Course toEntity(CourseDTO dto, Object... args) {
        return mapper.map(dto, Course.class);
    }
}

