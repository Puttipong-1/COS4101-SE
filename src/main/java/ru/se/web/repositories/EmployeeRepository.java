package ru.se.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import ru.se.web.model.Employee;

import javax.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findFirstByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Employee e SET e.firstname=:first,e.lastname=:last,e.phoneNumber=:number WHERE e.employeeId=:id")
    void updateProfile(@Param("id") long id, @Param("first") String first, @Param("last") String last, @Param("number") String number);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Employee e SET e.password=:password WHERE e.employeeId=:id")
    void changePassword(@Param("id") long id,@Param("password") String password);

    @Query(value = "SELECT e FROM Employee  e WHERE e.role=1 ORDER BY e.employeeId ASC")
    List<Employee> getAllEmployeeASC();

    Employee findFirstByEmployeeCode(String employeeCode);
    boolean existsByEmail(String email);

    default long countAllEmployeeRow(){
        return count();
    }
    default void addEmployee(Employee employee){
        save(employee);
    }

}
