package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseEmployee;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Qualifier("inMemory")
@Profile("test")
@Log
public class InMemoryEmployeeCommandRepositoryAdapter implements EmployeeCommandRepository {

    @Override
    public Integer add(Employee employee) {
    log.info("Try add into inMemoryDb employee: "+employee.toString());
        Integer id = DataBaseEmployee.getEmployeeHashMap().size() + 1;
        DataBaseEmployee.getEmployeeHashMap().put(id,employee);
        log.info("Succssec id = " + id);
        return id;
    }

    @Override
    public Employee update(Employee e) {
        return null;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return Optional.ofNullable(DataBaseEmployee.getEmployeeHashMap().get(id));
    }

    @Override
    public List<Employee> findAll() {
        return Collections.emptyList();
    }

    @Override
    public void delete(Integer id) {
        throw new NotImplementedException("Not implemented yet");
    }
}