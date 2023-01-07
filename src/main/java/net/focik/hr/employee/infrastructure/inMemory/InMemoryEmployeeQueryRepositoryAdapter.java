package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.query.EmployeeQueryBasicDto;
import net.focik.hr.employee.query.EmployeeQueryPrintDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component()
@Profile({"test"})
@Log
public class InMemoryEmployeeQueryRepositoryAdapter implements EmployeeQueryRepository {


    @Override
    public Optional<EmployeeQueryBasicDto> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<EmployeeQueryBasicDto> findAll() {
        return null;
    }

    @Override
    public List<EmployeeQueryBasicDto> findAllByEmploymentStatus(EmploymentStatus employmentStatus) {
        return null;
    }

    @Override
    public List<EmployeeQueryPrintDto> findAllByEmploymentStatusPrint(EmploymentStatus employmentStatus) {
        return null;
    }
}
