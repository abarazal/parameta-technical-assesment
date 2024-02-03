package co.parameta.parametasoapwebservice.endpoint;

import co.parameta.parametasoapwebservice.model.EmployeeEntity;
import co.parameta.parametasoapwebservice.service.EmployeeService;
import co.parameta.parametasoapwebservice.service.mapper.EmployeeMapper;
import co.parameta.web.assesment.employees.CreateEmployeeRequest;
import co.parameta.web.assesment.employees.CreateEmployeeResponse;
import co.parameta.web.assesment.employees.Employee;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://parameta.co/web/assesment/employees";

    private final EmployeeService employeeService;


    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEmployeeRequest")
    public CreateEmployeeResponse createEmployee(@RequestPayload CreateEmployeeRequest request) {

        EmployeeEntity employeeEntity = employeeService.createEmployee(request.getEmployee());
        Employee employee = EmployeeMapper.toEmployee(employeeEntity);

        CreateEmployeeResponse response = new CreateEmployeeResponse();
        response.setEmployee(employee);

        return response;
    }
}
