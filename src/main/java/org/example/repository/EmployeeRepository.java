package org.example.repository;

import org.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "select employee.id, employee.first_name, employee.last_name, employee.position, employee.department_name from employee \n" +
            "join employee_equipment on employee_equipment.employee_id = employee.id\n" +
            "join equipment on employee_equipment.equipment_id = equipment.id\n" +
            "where equipment.id = :equipmentId", nativeQuery = true)
    List<Employee> findEmployeesByEquipmentId(@Param("equipmentId") Long equipmentId);
}