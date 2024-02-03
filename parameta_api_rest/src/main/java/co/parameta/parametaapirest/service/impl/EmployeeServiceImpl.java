package co.parameta.parametaapirest.service.impl;

import co.parameta.parametaapirest.client.EmployeeClient;
import co.parameta.parametaapirest.controller.request.EmployeeRequest;
import co.parameta.parametaapirest.controller.response.EmployeeResponse;
import co.parameta.parametaapirest.service.EmployeeService;
import co.parameta.parametaapirest.service.mapper.EmployeeMapper;
import co.parameta.parametaapirest.wsdl.CreateEmployeeResponse;
import co.parameta.parametaapirest.wsdl.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeClient employeeClient;


    public EmployeeServiceImpl(EmployeeClient employeeClient) {
        this.employeeClient = employeeClient;
    }


    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        CreateEmployeeResponse createEmployeeResponse = employeeClient.createEmployee(employeeRequest);
        Employee employee = createEmployeeResponse.getEmployee();
        return EmployeeMapper.toEmployeeResponse(employee);
    }
}
