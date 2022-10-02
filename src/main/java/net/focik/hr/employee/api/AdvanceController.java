package net.focik.hr.employee.api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.focik.hr.employee.api.dto.AdvanceDto;
import net.focik.hr.employee.api.mapper.ApiAdvanceMapper;
import net.focik.hr.employee.domain.advance.Advance;
import net.focik.hr.employee.domain.advance.port.primary.AddAdvanceUseCase;
import net.focik.hr.employee.domain.advance.port.primary.DeleteAdvanceUseCase;
import net.focik.hr.employee.domain.advance.port.primary.GetAdvanceUseCase;
import net.focik.hr.utils.exceptions.AccessDeniedException;
import net.focik.hr.utils.exceptions.HttpResponse;
import net.focik.hr.utils.privileges.PrivilegeHelper;
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
@RequestMapping("/api/employee/salary/advance")
//@CrossOrigin
class AdvanceController {

    private final GetAdvanceUseCase getAdvanceUseCase;
    private final AddAdvanceUseCase addAdvanceUseCase;
    private final DeleteAdvanceUseCase deleteAdvanceUseCase;
    private final ModelMapper mapper;
    private final ApiAdvanceMapper advanceMapper;


    @GetMapping("/{idEmployee}/all")
    ResponseEntity<List<AdvanceDto>> getAdvancesByEmployeeAndDate(@PathVariable int idEmployee,
                                                                  @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                                  @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {

        log.info("Get advances for employee id: " + idEmployee);

        final List<String> accessRole = List.of(ROLE_ADMIN, HR_ADVANCE_READ_ALL, HR_ADVANCE_READ);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        List<Advance> advances = getAdvanceUseCase.getAdvances(idEmployee, date);

        return new ResponseEntity<>(advances.stream()
                .map(advanceMapper::toDto)
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addAdvance(@RequestBody AdvanceDto advanceDto,
                                              @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try add new advance.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_ADVANCE_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        Advance advance = advanceMapper.toDomain(advanceDto);
        Integer result = addAdvanceUseCase.addAdvance(advance);

        log.info(result > 0 ? "Advance added with id = " + result : "No advance added!");

        if (result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Integer> updateAdvance(@RequestBody AdvanceDto advanceDto,
                                                 @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try update advance.");
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_ADVANCE_WRITE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        Advance advance = advanceMapper.toDomain(advanceDto);
        Integer result = addAdvanceUseCase.updateAdvance(advance);

        log.info(result > 0 ? "Advance updated with id = " + result : "No advance updated!");

        if (result <= 0)
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idAdvance}")
    public ResponseEntity<HttpResponse> deleteAdvance(@PathVariable int idAdvance,
                                                      @RequestHeader(name = AUTHORITIES, required = false) String[] roles) {
        log.info("Try to delete advance with id: " + idAdvance);
        final List<String> accessRole = List.of(ROLE_ADMIN, HR_ADVANCE_DELETE_ALL);

        if (PrivilegeHelper.dontHaveAccess(List.of(roles), accessRole)) {
            throw new AccessDeniedException();
        }

        deleteAdvanceUseCase.deleteAdvanceById(idAdvance);

        log.info("Deleted advance with id = " + idAdvance);

        return response(HttpStatus.NO_CONTENT, "Zaliczka usunięta.");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase(), message);
        return new ResponseEntity<>(body, status);
    }
}