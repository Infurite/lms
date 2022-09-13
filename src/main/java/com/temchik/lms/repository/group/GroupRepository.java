package com.temchik.lms.repository.group;

import com.temchik.lms.model.group.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {

    boolean existsByIdAndTenantId(UUID id, UUID tenantId);

    Page<Group> getByTenantId(UUID tenantId, Pageable pageable);
}
