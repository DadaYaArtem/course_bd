package org.example.service;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeeByEquipmentId(Long id) {
        return employeeRepository.findEmployeesByEquipmentId(id);
    }
}
