package net.focik.hr.employee.query;

import lombok.RequiredArgsConstructor;
import net.focik.hr.employee.domain.port.secondary.EmployeeQueryRepository;
import net.focik.hr.employee.domain.share.EmploymentStatus;
import net.focik.hr.employee.infrastructure.dto.EmployeeDbDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class EmployeeQueryRepositoryAdapter implements EmployeeQueryRepository {

    public static final String MIN_DATE = "0001-01-01";
    private final EmployeeQueryDtoRepository queryDtoRepository;

    @Override
    public Optional<EmployeeQueryBasicDto> findById(Integer id) {

        Optional<EmployeeDbDto> byId = queryDtoRepository.findById(id);
        if (byId.isEmpty())
            return Optional.empty();

        return Optional.of(convertToBasicDto(List.of(byId.get())).get(0));
    }

    @Override
    public List<EmployeeQueryBasicDto> findAll() {
        List<EmployeeDbDto> allByEmploymentStatus = queryDtoRepository.findAll();
        return convertToBasicDto(allByEmploymentStatus);
    }

    @Override
    public List<EmployeeQueryBasicDto> findAllByEmploymentStatus(EmploymentStatus employmentStatus) {
        List<EmployeeDbDto> allByEmploymentStatus = queryDtoRepository
                .findAllByEmploymentStatus(employmentStatus.toString());
        return convertToBasicDto(allByEmploymentStatus);
    }

    @Override
    public List<EmployeeQueryPrintDto> findAllByEmploymentStatusPrint(EmploymentStatus employmentStatus) {
        List<EmployeeDbDto> allByEmploymentStatus = queryDtoRepository
                .findAllByEmploymentStatus(employmentStatus.toString());
        return convertToPrintDto(allByEmploymentStatus);
    }

    private List<EmployeeQueryBasicDto> convertToBasicDto(List<EmployeeDbDto> dbDtos) {
        List<EmployeeQueryBasicDto> basicDtos = new ArrayList<>();
        dbDtos.forEach(employeeDto -> basicDtos.add(EmployeeQueryBasicDto.builder()
                .id(employeeDto.getId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .street(employeeDto.getStreet())
                .city(employeeDto.getCity())
                .zip(employeeDto.getZip().trim())
                .numberDaysOffLeft(String.valueOf(employeeDto.getNumberDaysOffLeft()))
                .numberDaysOffAnnually(String.valueOf(employeeDto.getNumberDaysOffAnnually()))
                .telNumber(employeeDto.getTelNumber())
                .email(employeeDto.getEmail())
                .hiredDate(validateDate(employeeDto.getHiredDate()))
                .releaseDate(validateDate(employeeDto.getReleaseDate()))
                .nextMedicalExaminationDate(validateDate(employeeDto.getNextMedicalExaminationDate()))
                .nextBhpTrainingDate(validateDate(employeeDto.getNextBhpTrainingDate()))
                .employmentStatus(employeeDto.getEmploymentStatus().toString())
                .idTeam(employeeDto.getIdTeam())
                .pesel(employeeDto.getPesel() != null ? employeeDto.getPesel() : "")
                .employeeType(employeeDto.getEmployeeType().toString())
                .workTime(employeeDto.getWorkTime().toString())
                .otherInfo(employeeDto.getOtherInfo())
                .build()));

        return basicDtos;
    }

    private List<EmployeeQueryPrintDto> convertToPrintDto(List<EmployeeDbDto> dbDtos) {
        List<EmployeeQueryPrintDto> basicDtos = new ArrayList<>();
        dbDtos.forEach(employeeDto -> basicDtos.add(EmployeeQueryPrintDto.builder()
                .id(employeeDto.getId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .employmentStatus(employeeDto.getEmploymentStatus().toString())
                .employeeType(employeeDto.getEmployeeType().getViewValue())
                .workTime(employeeDto.getWorkTime().getViewValue())
                .build()));

        return basicDtos;
    }

    private LocalDate validateDate(LocalDate date) {
        if (date == null)
            return null;
        if (MIN_DATE.equals(date.toString()))
            return null;
        else return date;
    }


}
