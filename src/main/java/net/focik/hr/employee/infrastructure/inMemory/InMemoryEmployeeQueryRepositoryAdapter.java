package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component()
@Profile({"dev", "test"})
@Log
public class InMemoryEmployeeQueryRepositoryAdapter implements EmployeeQueryRepository {

    @Override
    public Optional<Employee> findById(Integer id) {
        return Optional.ofNullable(DataBase.getEmployeeHashMap().get(id));
    }
}
