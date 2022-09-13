package com.temchik.lms.service.group;

import com.temchik.lms.common.exception.BusinessException;
import com.temchik.lms.config.tenant.TenantContext;
import com.temchik.lms.dto.group.GroupDTO;
import com.temchik.lms.model.group.Group;
import com.temchik.lms.repository.group.GroupRepository;
import com.temchik.lms.service.mapper.group.GroupDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;
    private final GroupDTOMapper mapper;

    public Page<GroupDTO> get(Pageable pageable) {
        return repository.getByTenantId(TenantContext.getTenantId(), pageable).map(mapper::toDTO);
    }

    @Transactional
    public GroupDTO create(GroupDTO dio) {
        Group group = mapper.toEntity(dio);
        group.setTenantId(TenantContext.getTenantId());
        group.setCreatedAt(new Date(System.currentTimeMillis()));
        return mapper.toDTO(repository.save(group));
    }

    public void delete(UUID groupId) {
        if (repository.existsByIdAndTenantId(groupId, TenantContext.getTenantId())) {
            repository.deleteById(groupId);
        } else {
            throw new BusinessException("Group can't be deleted!");
        }
    }
}
