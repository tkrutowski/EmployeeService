package net.focik.hr.employee.query;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.infrastructure.dto.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
class EmployeeQueryRepositoryAdapter implements EmployeeQueryRepository {

    private EmployeeQueryDtoRepository queryDtoRepository;

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
        List<EmployeeQueryBasicDto> basicDtos = new ArrayList<>();
        String s = employmentStatus.toString();
        List<EmployeeDto> allByEmploymentStatus = queryDtoRepository.findAllByEmploymentStatus(s);
        allByEmploymentStatus.stream()
                .forEach(employeeDto -> basicDtos.add(new EmployeeQueryBasicDto(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName())));

        return basicDtos;
    }
}
