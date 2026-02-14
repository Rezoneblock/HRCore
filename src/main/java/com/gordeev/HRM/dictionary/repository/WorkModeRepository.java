package com.gordeev.HRM.dictionary.repository;

import com.gordeev.HRM.dictionary.entity.WorkMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkModeRepository extends JpaRepository<WorkMode, Long> {
}
