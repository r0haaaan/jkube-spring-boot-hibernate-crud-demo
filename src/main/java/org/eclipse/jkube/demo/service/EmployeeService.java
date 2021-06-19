package org.eclipse.jkube.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.eclipse.jkube.demo.exception.RecordNotFoundException;
import org.eclipse.jkube.demo.model.EmployeeEntity;
import org.eclipse.jkube.demo.repository.EmployeeRepository;
 
@Service
public class EmployeeService {
     
    @Autowired
    EmployeeRepository repository;
     
    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> employeeList = repository.findAll();
         
        if(employeeList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return employeeList;
        }
    }
     
    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public EmployeeEntity createEmployee(EmployeeEntity entity) {
        return repository.save(entity);
    }
     
    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}