package co.parameta.parametaapirest.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeePeriod {

    private Integer year;
    private Integer month;
    private Integer days;
}
