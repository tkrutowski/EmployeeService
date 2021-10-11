package net.focik.hr.employee.infrastructure.inMemory;

import lombok.extern.java.Log;
import net.focik.hr.employee.domain.Employee;
import net.focik.hr.employee.domain.port.secondary.EmployeeCommandRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("inMemory")
//@Profile("test")
@Log
public class InMemoryEmployeeCommandRepositoryAdapter implements EmployeeCommandRepository {

    @Override
    public Integer add(Employee employee) {
    log.info("Try add into inMemoryDb employee: "+employee.toString());
        if(employee == null)
            throw new NullPointerException("Employee cannot be null");
        Integer id = DataBase.getEmployeeHashMap().size() + 1;
       // employee.setId(id);
        DataBase.getEmployeeHashMap().put(id,employee);
        log.info("Succssec id = " + id);
        return id;
    }

//    @Override
//    public Optional<Employee> findById(Integer id) {
//        return Optional.ofNullable(DataBase.getEmployeeHashMap().get(id));
//    }
}
