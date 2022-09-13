package com.temchik.lms.repository.group;

import com.temchik.lms.model.group.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    boolean existsByCourseIdAndNameIgnoreCase(UUID courseID, String name);

    Page<Lesson> findByCourseId(Pageable pageable, UUID courseId);
}
