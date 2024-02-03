package co.parameta.parametasoapwebservice.repository;


import co.parameta.parametasoapwebservice.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

}
