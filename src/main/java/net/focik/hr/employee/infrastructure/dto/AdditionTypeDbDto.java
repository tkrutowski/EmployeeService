package net.focik.hr.employee.infrastructure.dto;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rodzaj_dodatku")
@Getter
@Setter
@ToString
public class AdditionTypeDbDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rodzaj_dodatku")
    private Integer id;
    @Column(name = "nazwa")
    private String name;

}
