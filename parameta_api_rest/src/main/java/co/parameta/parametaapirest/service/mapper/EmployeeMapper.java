package co.parameta.parametaapirest.service.mapper;

import co.parameta.parametaapirest.controller.request.EmployeeRequest;
import co.parameta.parametaapirest.controller.response.EmployeePeriod;
import co.parameta.parametaapirest.controller.response.EmployeeResponse;
import co.parameta.parametaapirest.util.DateUtil;
import co.parameta.parametaapirest.wsdl.CreateEmployeeRequest;
import co.parameta.parametaapirest.wsdl.Employee;

import java.time.LocalDate;
import java.time.Period;

public class EmployeeMapper {

    public static CreateEmployeeRequest toCreateEmployeeRequest(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setFirstNames(employeeRequest.getFirstNames());
        employee.setLastNames(employeeRequest.getLastNames());
        employee.setDocumentType(employeeRequest.getDocumentType());
        employee.setDocumentNumber(employeeRequest.getDocumentNumber());
        employee.setBirthDate(DateUtil.toGregorianCalendar(employeeRequest.getBirthDate()));
        employee.setHireDate(DateUtil.toGregorianCalendar(employeeRequest.getHireDate()));
        employee.setPosition(employeeRequest.getPosition());
        employee.setSalary(employeeRequest.getSalary());

        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        createEmployeeRequest.setEmployee(employee);

        return createEmployeeRequest;
    }


    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setFirstNames(employee.getFirstNames());
        employeeResponse.setLastNames(employee.getLastNames());
        employeeResponse.setDocumentType(employee.getDocumentType());
        employeeResponse.setDocumentNumber(employee.getDocumentNumber());

        LocalDate birthDate = DateUtil.toLocalDate(employee.getBirthDate());
        employeeResponse.setBirthDate(birthDate);

        LocalDate hireDate = DateUtil.toLocalDate(employee.getHireDate());
        employeeResponse.setHireDate(hireDate);

        employeeResponse.setPosition(employee.getPosition());
        employeeResponse.setSalary(employee.getSalary());

        Period employmentPeriod = Period.between(hireDate, LocalDate.now());
        employeeResponse.setEmploymentPeriod(new EmployeePeriod(employmentPeriod.getYears(), employmentPeriod.getMonths(), employmentPeriod.getDays()));

        Period currentAge = Period.between(birthDate, LocalDate.now());
        employeeResponse.setCurrentAge(new EmployeePeriod(currentAge.getYears(), currentAge.getMonths(), currentAge.getDays()));

        return employeeResponse;
    }


    private EmployeeMapper() {}
}
