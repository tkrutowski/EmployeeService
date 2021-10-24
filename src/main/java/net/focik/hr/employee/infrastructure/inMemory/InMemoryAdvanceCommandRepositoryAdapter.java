package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.finances.Advance;
import net.focik.hr.employee.domain.finances.port.secondary.AdvanceCommandRepository;
import net.focik.hr.employee.infrastructure.dto.AdvanceDto;
import net.focik.hr.employee.infrastructure.dto.JpaMapper;
import net.focik.hr.employee.infrastructure.inMemory.db.DataBaseAdvances;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("inMemory")
@Profile("test")
@Log
public class InMemoryAdvanceCommandRepositoryAdapter implements AdvanceCommandRepository {

    JpaMapper jpaMapper=new JpaMapper();

    @Override
    public Integer add(Advance advance, Integer idEmployee) {
        AdvanceDto advanceDto = jpaMapper.toDto(advance, idEmployee);
        log.info("Try add into inMemoryDb advance: " + advanceDto.toString());
        if (advanceDto == null)
            throw new NullPointerException("Advance cannot be null");
        Integer id = DataBaseAdvances.getAdvancesHashMap().size() + 1;
        // employee.setId(id);
        DataBaseAdvances.getAdvancesHashMap().put(id, advanceDto);
        log.info("Succssec id = " + id);
        return id;
    }
}
