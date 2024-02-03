package co.parameta.parametasoapwebservice.service.mapper;

import co.parameta.parametasoapwebservice.model.EmployeeEntity;
import co.parameta.parametasoapwebservice.util.DateUtil;
import co.parameta.web.assesment.employees.Employee;

public class EmployeeMapper {

    public static EmployeeEntity toEmployeeEntity(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstNames(employee.getFirstNames());
        employeeEntity.setLastNames(employee.getLastNames());
        employeeEntity.setDocumentType(employee.getDocumentType());
        employeeEntity.setDocumentNumber(employee.getDocumentNumber());
        employeeEntity.setBirthDate(DateUtil.toLocalDate(employee.getBirthDate()));
        employeeEntity.setHireDate(DateUtil.toLocalDate(employee.getHireDate()));
        employeeEntity.setPosition(employee.getPosition());
        employeeEntity.setSalary(employee.getSalary());
        return employeeEntity;
    }

    public static Employee toEmployee(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setFirstNames(employeeEntity.getFirstNames());
        employee.setLastNames(employeeEntity.getLastNames());
        employee.setDocumentType(employeeEntity.getDocumentType());
        employee.setDocumentNumber(employeeEntity.getDocumentNumber());
        employee.setBirthDate(DateUtil.toGregorianCalendar(employeeEntity.getBirthDate()));
        employee.setHireDate(DateUtil.toGregorianCalendar(employeeEntity.getHireDate()));
        employee.setPosition(employeeEntity.getPosition());
        employee.setSalary(employeeEntity.getSalary());
        return employee;
    }


    private EmployeeMapper() {}
}
