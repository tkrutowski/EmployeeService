package net.focik.hr.employee.query;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
class EmployeeQueryRepositoryAdapter implements EmployeeQueryRepository {

    private EmployeeQueryDtoRepository queryDtoRepository;

    @Override
    public Optional<EmployeeQueryDto> findById(Integer id) {
        return queryDtoRepository.findById(id);
    }

    @Override
    public List<EmployeeQueryDto> findAll() {
        return queryDtoRepository.findAll();
    }
}
