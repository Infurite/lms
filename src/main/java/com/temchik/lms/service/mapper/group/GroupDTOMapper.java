package com.temchik.lms.service.mapper.group;

import com.temchik.lms.dto.group.GroupDTO;
import com.temchik.lms.model.group.Group;
import com.temchik.lms.service.mapper.EntityToDTOMapper;
import com.temchik.lms.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class GroupDTOMapper implements EntityToDTOMapper<GroupDTO, Group, GroupDTO> {

    private final ModelMapper mapper = new ModelMapper();
    private final UserService userService;

    public GroupDTOMapper(UserService userService) {
        this.userService = userService;


        mapper.addMappings(new PropertyMap<GroupDTO, Group>() {
            protected void configure() {
                skip().setId(null);
                skip().setUsers(null);
            }
        });
        mapper.addMappings(new PropertyMap<Group, GroupDTO>() {
            protected void configure() {
                skip().setUserIds(null);
            }
        });

    }

    @Override
    public GroupDTO toDTO(Group entity, Object... args) {
        return mapper.map(entity, GroupDTO.class);
    }

    @Override
    public Group toEntity(GroupDTO dto, Object... args) {
        Group group = mapper.map(dto, Group.class);
        if (dto.getUserIds() != null) {
            group.setUsers(dto.getUserIds()
                    .stream()
                    .map(userService::findByIdAndCurrentTenant)
                    .collect(Collectors.toList()));
        }
        group.setUpdatedAt(new Date(System.currentTimeMillis()));
        return group;
    }
}
