package net.focik.hr.employee.domain.addition;

import lombok.AllArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class AdditionFacade {

    private final AdditionService additionService;

    public Money getAdditionsSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        return additionService.getAdditionsSumByIdEmployeeAndDate(idEmployee, date);
    }

    public Integer addAddition(Addition addition) {
        return additionService.addAddition(addition);
    }

    public List<AdditionType> findAdditionTypes() {
        return additionService.getAdditionTypes();
    }

    public List<Addition> getAdditions(Integer idEmployee, LocalDate date) {
        return additionService.findByEmployeeIdAndDate(idEmployee, date);
    }

    public List<AdditionType> getAdditionTypes() {
        return additionService.findAll();
    }


    public Integer updateAddition(Addition addition) {
        return additionService.updateAddition(addition);
    }

    public void deleteAddition(int id) {
        additionService.deleteAddition(id);
    }

    public Integer addAdditionType(AdditionType additionType) {
        return additionService.add(additionType);
    }
}