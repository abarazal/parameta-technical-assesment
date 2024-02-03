package co.parameta.parametaapirest.client;

import co.parameta.parametaapirest.controller.request.EmployeeRequest;
import co.parameta.parametaapirest.service.mapper.EmployeeMapper;
import co.parameta.parametaapirest.wsdl.CreateEmployeeRequest;
import co.parameta.parametaapirest.wsdl.CreateEmployeeResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class EmployeeClient extends WebServiceGatewaySupport {

    public CreateEmployeeResponse createEmployee(EmployeeRequest employeeRequest) {

        CreateEmployeeRequest request = EmployeeMapper.toCreateEmployeeRequest(employeeRequest);

        return (CreateEmployeeResponse) getWebServiceTemplate().marshalSendAndReceive(
                "http://localhost:8080/ws/employees",
                request,
                new SoapActionCallback("http://parameta.co/web/assesment/employees/createEmployeeRequest")
        );
    }
}
