package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.port.secondary.EmployeeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component()
@Profile({"dev", "test"})
@Log
public class InMemoryEmployeeRepositoryAdapter implements EmployeeRepository {
    private HashMap<Integer, Employee> employeeHashMap = new HashMap<>();


    @Override
    public Integer add(Employee employee) {
        int i=0;
    log.info("Try add into inMemoryDb employee: "+employee.toString());
        if(employee == null)
            throw new NullPointerException("Employee cannot be null");
        Integer id = employeeHashMap.size() + 1;
        employee.setId(id);
        employeeHashMap.put(id,employee);
        log.info("Succssec id = " + id);
        return id;
    }


    @Override
    public Optional<Employee> findById(Integer id) {
        return Optional.ofNullable(employeeHashMap.get(id));
    }
}
