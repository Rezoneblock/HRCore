package com.gordeev.HRM.dictionary.repository;

import com.gordeev.HRM.dictionary.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByCodeIn(List<String> codes);
    Boolean existsByCode(String code);

    @Query("""
        select e from Department e
        where lower(e.code) like lower(concat('%', :code, '%'))
""")
    Page<Department> findByCode(@Param("code") String code, Pageable pageable);
}
