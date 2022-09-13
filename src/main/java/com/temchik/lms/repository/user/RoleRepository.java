package com.temchik.lms.repository.user;

import com.temchik.lms.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByIsDefaultTrue();

    boolean existsByIsDefaultTrueAndNameIgnoreCase(String name);

    boolean existsByIsDefaultTrueAndId(UUID id);
}
