package me.rasfi.employeemanager.service;

import me.rasfi.employeemanager.exception.UserNotFoundException;
import me.rasfi.employeemanager.model.Employee;
import me.rasfi.employeemanager.repositories.EmployeeRepo;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id)
    {
        employeeRepo.deleteEmployeeById(id);
    }

    public Employee findEmployeeById(Long id)
    {
        return employeeRepo.findEmployeeBydId(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found."));
    }
}