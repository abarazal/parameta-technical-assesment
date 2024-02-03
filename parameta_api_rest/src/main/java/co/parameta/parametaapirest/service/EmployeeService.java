package co.parameta.parametaapirest.service;

import co.parameta.parametaapirest.controller.request.EmployeeRequest;
import co.parameta.parametaapirest.controller.response.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
}
