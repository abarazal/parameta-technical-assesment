package co.parameta.parametasoapwebservice.service.impl;

import co.parameta.parametasoapwebservice.model.EmployeeEntity;
import co.parameta.parametasoapwebservice.repository.EmployeeRepository;
import co.parameta.parametasoapwebservice.service.EmployeeService;
import co.parameta.parametasoapwebservice.service.mapper.EmployeeMapper;
import co.parameta.web.assesment.employees.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeEntity createEmployee(Employee employee) {

        EmployeeEntity employeeEntity = EmployeeMapper.toEmployeeEntity(employee);

        return employeeRepository.save(employeeEntity);
    }
}
