package net.focik.hr.employee.query;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.infrastructure.dto.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class EmployeeQueryRepositoryAdapter implements EmployeeQueryRepository {

    private final EmployeeQueryDtoRepository queryDtoRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<EmployeeQueryBasicDto> findById(Integer id) {

        Optional<EmployeeDto> byId = queryDtoRepository.findById(id);
        if(byId.isEmpty())
            return Optional.empty();

        return Optional.of(mapper.map(byId.get(), EmployeeQueryBasicDto.class));
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
                .forEach(employeeDto -> basicDtos.add(EmployeeQueryBasicDto.builder()
                        .id(employeeDto.getId())
                        .firstName(employeeDto.getFirstName())
                        .lastName(employeeDto.getLastName())
                        .email(employeeDto.getEmail())
                        .idTeam(employeeDto.getIdTeam())
                        .build()));

        return basicDtos;
    }
}
