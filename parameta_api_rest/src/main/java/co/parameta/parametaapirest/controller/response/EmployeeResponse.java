package co.parameta.parametaapirest.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponse {

    private Integer id;
    private String firstNames;
    private String lastNames;
    private String documentType;
    private Integer documentNumber;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String position;
    private Double salary;

    private EmployeePeriod employmentPeriod;
    private EmployeePeriod currentAge;
}
