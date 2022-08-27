package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.AdditionDto;
import net.focik.hr.employee.api.dto.AdditionTypeDto;
import net.focik.hr.employee.api.mapper.ApiAdditionMapper;
import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.AdditionType;
import net.focik.hr.employee.domain.addition.port.primary.AddAdditionUseCase;
import net.focik.hr.employee.domain.addition.port.primary.DeleteAdditionUseCase;
import net.focik.hr.employee.domain.addition.port.primary.GetAdditionUseCase;
import net.focik.hr.employee.domain.exceptions.AdditionTypeNotValidException;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.exceptions.HttpResponse;
import net.focik.hr.utils.privileges.PrivilegeHelper;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static net.focik.hr.utils.privileges.PrivilegeHelper.*;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/employee/salary/addition")
//@CrossOrigin
class AdditionController {

    private final GetAdditionUseCase getAdditionUseCase;
    private final AddAdditionUseCase addAdditionUseCase;
    private final DeleteAdditionUseCase deleteAdditionUseCase;
    private final ModelMapper mapper;
    private final ApiAdditionMapper additionMapper;


    @GetMapping("/{idEmployee}/all")
    ResponseEntity<List<AdditionDto>> getAdditionsByEmployeeAndDate(@PathVariable int idEmployee,
                                                                    @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                                    @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get additions for employee id: " + idEmployee);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_ADDITION_READ_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }


        List<Addition> additions = getAdditionUseCase.getAdditions(idEmployee, date);

        return new ResponseEntity<>(additions.stream()
                .map(additionMapper::toDto)
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }


    @GetMapping("/additiontype")
    ResponseEntity<List<AdditionTypeDto>> getEmployeeTypesView() {

        List<AdditionTypeDto> types = getAdditionUseCase.getAdditionTypes().stream()
                .map(additionType -> mapper.map(additionType, AdditionTypeDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addAddition(@RequestBody AdditionDto additionDto,
                                               @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try add new addition.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_ADDITION_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        Addition addition = additionMapper.toDomain(additionDto);
        Integer result = addAdditionUseCase.addAddition(addition);

        log.info(result > 0 ? "Addition added with id = " + result : "No addition added!");

        if (result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/additiontype")
    public ResponseEntity<Integer> addAdditionType(@RequestParam(value = "typeName") String typeName,
                                                   @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try add new addition type.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_ADDITION_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        if (StringUtils.isEmpty(typeName))
            throw new AdditionTypeNotValidException("Nazwa nie może być pusta.");


        AdditionType type = new AdditionType(null, typeName);
        Integer result = addAdditionUseCase.addAdditionType(type);

        log.info(result > 0 ? "Addition type added with id = " + result : "No addition type added!");

        if (result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<Integer> updateAddition(@RequestBody AdditionDto additionDto,
                                                  @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try update new addition.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_ADDITION_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        Addition addition = additionMapper.toDomain(additionDto);
        Integer result = addAdditionUseCase.updateAddition(addition);

        log.info(result > 0 ? "Addition updated with id = " + result : "No addition updated!");

        if (result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idAddition}")
    public ResponseEntity<HttpResponse> deleteAddition(@PathVariable int idAddition,
                                                       @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try to delete additin with id: " + idAddition);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_ADDITION_DELETE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        deleteAdditionUseCase.deleteAdditionById(idAddition);

        log.info("Deleted addition with id = " + idAddition);

        return response(HttpStatus.NO_CONTENT, "Dodatek usunięty.");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase(), message);
        return new ResponseEntity<>(body, status);
    }
}
