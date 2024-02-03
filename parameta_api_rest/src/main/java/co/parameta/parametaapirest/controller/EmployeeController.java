package co.parameta.parametaapirest.controller;

import co.parameta.parametaapirest.controller.request.EmployeeRequest;
import co.parameta.parametaapirest.controller.response.EmployeeResponse;
import co.parameta.parametaapirest.service.EmployeeService;
import co.parameta.parametaapirest.validation.IsLegalAge;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static co.parameta.parametaapirest.constant.ErrorConstant.MUST_NOT_BE_BLANK;
import static co.parameta.parametaapirest.constant.ErrorConstant.MUST_NOT_BE_NULL;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse createEmployee(@NotBlank(message = MUST_NOT_BE_BLANK) @RequestParam(name = "first_names") String firstNames,
                                           @NotBlank(message = MUST_NOT_BE_BLANK) @RequestParam(name = "last_names") String lastNames,
                                           @NotBlank(message = MUST_NOT_BE_BLANK) @RequestParam(name = "document_type") String documentType,
                                           @NotBlank(message = MUST_NOT_BE_BLANK) @RequestParam(name = "document_number") String documentNumber,
                                           @IsLegalAge @RequestParam(name = "birth_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate,
                                           @RequestParam(name = "hire_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hireDate,
                                           @NotBlank(message = MUST_NOT_BE_BLANK) @RequestParam String position,
                                           @NotNull(message = MUST_NOT_BE_NULL) @RequestParam Double salary) {

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setFirstNames(firstNames);
        employeeRequest.setLastNames(lastNames);
        employeeRequest.setDocumentType(documentType);
        employeeRequest.setDocumentNumber(Integer.parseInt(documentNumber));
        employeeRequest.setBirthDate(birthDate);
        employeeRequest.setHireDate(hireDate);
        employeeRequest.setPosition(position);
        employeeRequest.setSalary(salary);

        return employeeService.createEmployee(employeeRequest);
    }
}
