package net.focik.hr.employee.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface EmployeeQueryDtoRepository extends JpaRepository<EmployeeQueryDto, Integer> {

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
