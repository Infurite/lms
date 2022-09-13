package com.temchik.lms.repository.group;

import com.temchik.lms.model.group.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    boolean existsByGroupIdAndNameIgnoreCase(UUID groupId, String name);

    Page<Course> findByGroupId(Pageable pageable, UUID groupId);
}

