package co.parameta.parametasoapwebservice.service;

import co.parameta.parametasoapwebservice.model.EmployeeEntity;
import co.parameta.web.assesment.employees.Employee;

public interface EmployeeService {

    EmployeeEntity createEmployee(Employee employee);
}
