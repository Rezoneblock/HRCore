package com.gordeev.HRM.dictionary.service;

import com.gordeev.HRM.dictionary.repository.EmploymentModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmploymentModeService {
    private final EmploymentModeRepository employmentModeRepository;
}
