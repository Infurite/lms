package com.temchik.lms.repository.user;

import com.temchik.lms.common.constants.account.PrivilegeAction;
import com.temchik.lms.common.constants.account.PrivilegeResource;
import com.temchik.lms.model.user.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {

    boolean existsByResourceAndAction(PrivilegeResource resource, PrivilegeAction action);
}
