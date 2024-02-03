package co.parameta.parametaapirest.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRequest {

    private String firstNames;
    private String lastNames;
    private String documentType;
    private Integer documentNumber;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String position;
    private Double salary;
}
