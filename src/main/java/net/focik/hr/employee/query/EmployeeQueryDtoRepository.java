package net.focik.hr.employee.query;

import net.focik.hr.employee.infrastructure.dto.EmployeeDbDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface EmployeeQueryDtoRepository extends JpaRepository<EmployeeDbDto, Integer> {

//    @Query(value = "select id_pracownika, imie, nazwisko from pracownik where zatrudniony = ?1 order by nazwisko asc",
    @Query(value = "select * from pracownik  where zatrudniony = ?1 order by nazwisko asc",
//    @Query(value = "select * from pracownik ",
            nativeQuery = true)
    List<EmployeeDbDto> findAllByEmploymentStatus(String status);

//    @Override
//    List<EmployeeQueryDto> findAll();
//
//    @Override
//    Optional<EmployeeQueryDto> findById(Integer integer);

//    TypedQuery<Item> query = em.createQuery(
////            "select i from Item i where i.id = :id", Item.class
////    ).setParameter("id", ITEM_ID);
////    Item item = query.getSingleResult();

    //ZWRACA JEDEN WIERSZ
//    try {
//        TypedQuery<Item> query = em.createQuery(
//                "select i from Item i where i.id = :id", Item.class
//        ).setParameter("id", 1234l);
//        Item item = query.getSingleResult();
//// …GDY NIE MA
//    } catch (NoResultException ex) {
//// …
//    }
    //GDY JEST KILKA
// catch (NonUniqueResultException ex) {
//// …
//        }

}
