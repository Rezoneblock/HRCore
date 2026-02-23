package com.gordeev.HRM.dictionary.service;

import com.gordeev.HRM.common.exception.ResourceAlreadyExistsException;
import com.gordeev.HRM.common.exception.ResourceDoesNotExistException;
import com.gordeev.HRM.dictionary.dto.request.EmploymentTypesCreateRequest.EmploymentTypesCreateRequest;
import com.gordeev.HRM.dictionary.dto.response.employmentType.EmploymentTypeResponse;
import com.gordeev.HRM.dictionary.entity.EmploymentType;
import com.gordeev.HRM.dictionary.mapper.EmploymentTypeMapper;
import com.gordeev.HRM.dictionary.repository.EmploymentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmploymentTypeService {
    private final EmploymentTypeRepository employmentTypeRepository;
    private final EmploymentTypeMapper employmentTypeMapper;

    @Transactional
    public List<EmploymentTypeResponse> createEmploymentTypes(EmploymentTypesCreateRequest request) {
        List<EmploymentType> employmentTypes = new ArrayList<>();

        request.codes().forEach(code -> {
            if (employmentTypeRepository.existsByCode(code)) {
                throw new ResourceAlreadyExistsException("Employment mode with code " + code + " already exists");
            }
            employmentTypes.add(EmploymentType.builder().code(code).build());
        });

        List<EmploymentType> saved = employmentTypeRepository.saveAll(employmentTypes);

        return saved.stream().map(employmentTypeMapper::toResponse).toList();
    }

    public List<EmploymentTypeResponse> getEmploymentTypes() {
        return employmentTypeRepository.findAll().stream().map(employmentTypeMapper::toResponse).toList();
    }

    @Transactional
    public void deleteEmploymentType(String code) {
        if (!employmentTypeRepository.existsByCode(code)) {
            throw new ResourceDoesNotExistException("EmploymentType with code " + code + " does not exist");
        }
        EmploymentType employmentType = employmentTypeRepository.findByCode(code);
        employmentTypeRepository.delete(employmentType);
    }
}
