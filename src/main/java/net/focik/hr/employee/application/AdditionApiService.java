package net.focik.hr.employee.application;

import lombok.AllArgsConstructor;
import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.AdditionFacade;
import net.focik.hr.employee.domain.addition.AdditionType;
import net.focik.hr.employee.domain.addition.port.primary.AddAdditionUseCase;
import net.focik.hr.employee.domain.addition.port.primary.DeleteAdditionUseCase;
import net.focik.hr.employee.domain.addition.port.primary.GetAdditionUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class AdditionApiService implements GetAdditionUseCase, AddAdditionUseCase, DeleteAdditionUseCase {

    private final AdditionFacade additionFacade;

    @Override
    public Addition getAddition(Integer id) {
        return null;
    }

    @Override
    public List<Addition> getAdditions(Integer idEmployee, LocalDate date) {
        return additionFacade.getAdditions(idEmployee, date);
    }

    @Override
    public List<AdditionType> getAdditionTypes() {
        return additionFacade.getAdditionTypes();
    }

    @Override
    public Integer addAddition(Addition addition) {
        return additionFacade.addAddition(addition);
    }

    @Override
    public Integer addAdditionType(AdditionType additionType) {
        return additionFacade.addAdditionType(additionType);
    }

    @Override
    public Integer updateAddition(Addition addition) {
        return additionFacade.updateAddition(addition);
    }

    @Override
    public void deleteAdditionById(int id) {
        additionFacade.deleteAddition(id);
    }
}