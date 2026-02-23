package com.gordeev.HRM.dictionary.service;

import com.gordeev.HRM.common.exception.ResourceAlreadyExistsException;
import com.gordeev.HRM.dictionary.dto.request.employmentMode.EmploymentModesCreateRequest;
import com.gordeev.HRM.dictionary.dto.response.employmentMode.EmploymentModeResponse;
import com.gordeev.HRM.dictionary.entity.EmploymentMode;
import com.gordeev.HRM.dictionary.mapper.EmploymentModeMapper;
import com.gordeev.HRM.dictionary.repository.EmploymentModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmploymentModeService {
    private final EmploymentModeRepository employmentModeRepository;
    private final EmploymentModeMapper employmentModeMapper;

    @Transactional
    public List<EmploymentModeResponse> createEmploymentModes(EmploymentModesCreateRequest request) {
        List<EmploymentMode> employmentModes = new ArrayList<>();

        request.codes().forEach(code -> {
            if (employmentModeRepository.existsByCode(code)) {
                throw new ResourceAlreadyExistsException("Employment mode with code " + code + " already exists");
            }
            employmentModes.add(EmploymentMode.builder().code(code).build());
        });

        List<EmploymentMode> saved = employmentModeRepository.saveAll(employmentModes);

        return saved.stream().map(employmentModeMapper::toResponse).toList();
    }
}
