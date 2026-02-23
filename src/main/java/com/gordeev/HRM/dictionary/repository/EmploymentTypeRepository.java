package com.gordeev.HRM.dictionary.repository;

import com.gordeev.HRM.dictionary.entity.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Long> {
    Boolean existsByCode(String code);

    EmploymentType findByCode(String code);

//    List<EmploymentType> findByCodeIn(List<String> codes);
}
