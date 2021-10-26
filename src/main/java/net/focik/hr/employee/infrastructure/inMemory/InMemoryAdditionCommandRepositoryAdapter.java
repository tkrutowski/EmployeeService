package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.addition.Addition;
import net.focik.hr.employee.domain.addition.port.secondary.AdditionCommandRepository;
import net.focik.hr.employee.infrastructure.dto.AdditionDto;
import net.focik.hr.employee.infrastructure.dto.JpaMapper;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseAddition;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseAdvances;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("inMemory")
@Profile("test")
@Log
public class InMemoryAdditionCommandRepositoryAdapter implements AdditionCommandRepository {

    JpaMapper jpaMapper=new JpaMapper();

    @Override
    public Integer add(Addition addition, Integer idEmployee) {
        AdditionDto additionDto = jpaMapper.toDto(addition, idEmployee);
        log.info("Try add into inMemoryDb addition: " + additionDto.toString());
        if (additionDto == null)
            throw new NullPointerException("Addition cannot be null");
        Integer id = DataBaseAddition.getAdditionsHashMap().size() + 1;
        // employee.setId(id);
        DataBaseAddition.getAdditionsHashMap().put(id, additionDto);
        log.info("Succssec id = " + id);
        return id;
    }
}
