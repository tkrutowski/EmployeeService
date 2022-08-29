package net.focik.hr.employee.domain.addition;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.addition.port.secondary.AdditionRepository;
import net.focik.hr.employee.domain.addition.port.secondary.AdditionTypeRepository;
import net.focik.hr.employee.domain.exceptions.AdditionNotValidException;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
class AdditionService {

    private final AdditionRepository additionRepository;
    private final AdditionTypeRepository additionTypeRepository;

    Money getAdditionsSumByIdEmployeeAndDate(int idEmployee, LocalDate date) {
        Money sum = Money.of(0, "PLN");
        List<Addition> byIdEmployeeAndDate = additionRepository.findByEmployeeIdAndDate(idEmployee, date);

        if (byIdEmployeeAndDate != null && !byIdEmployeeAndDate.isEmpty()) {
            for (Addition a : byIdEmployeeAndDate) {
                sum = sum.add(Money.of(a.getAmount(), "PLN"));
            }
        }
        return sum;
    }

    Integer addAddition(Addition addition) {
        if (isNotValid(addition))
            throw new AdditionNotValidException("Date can't be null.");
        return additionRepository.addAddition(addition);
    }

    public List<AdditionType> getAdditionTypes() {
        return additionTypeRepository.findAll();
    }

    public List<Addition> findByEmployeeIdAndDate(Integer idEmployee, LocalDate date) {
        return additionRepository.findByEmployeeIdAndDate(idEmployee, date);
    }

    public Integer updateAddition(Addition addition) {
        return additionRepository.updateAddition(addition);
    }

    public void deleteAddition(int id) {
        additionRepository.deleteAddition(id);
    }

    public List<AdditionType> findAll() {
        return additionTypeRepository.findAll();
    }

    public Integer add(AdditionType additionType) {
        return additionTypeRepository.add(additionType);
    }

    private boolean isNotValid(Addition a) {
        return (a.getAmount().equals(BigDecimal.ZERO) || a.getDate() == null);
    }
}
