package com.temchik.lms.service.mapper.group;

import com.temchik.lms.dto.group.LessonDTO;
import com.temchik.lms.model.group.Lesson;
import com.temchik.lms.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class LessonDTOMapper implements EntityToDTOMapper<LessonDTO, Lesson, LessonDTO> {

    private final ModelMapper mapper = new ModelMapper();

    public LessonDTOMapper() {
        mapper.addMappings(new PropertyMap<LessonDTO, Lesson>() {
            protected void configure() {
                skip().setId(null);
            }
        });
    }

    @Override
    public LessonDTO toDTO(Lesson entity, Object... args) {
        return mapper.map(entity, LessonDTO.class);
    }

    @Override
    public Lesson toEntity(LessonDTO dto, Object... args) {
        return mapper.map(dto, Lesson.class);
    }
}
