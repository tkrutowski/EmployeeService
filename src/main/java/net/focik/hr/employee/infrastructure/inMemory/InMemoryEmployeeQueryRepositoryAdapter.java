package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import net.focik.hr.employee.query.EmployeeQueryDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component()
@Profile({"test"})
@Log
public class InMemoryEmployeeQueryRepositoryAdapter implements EmployeeQueryRepository {


    @Override
    public Optional<EmployeeQueryDto> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<EmployeeQueryDto> findAll() {
        return null;
    }
}
