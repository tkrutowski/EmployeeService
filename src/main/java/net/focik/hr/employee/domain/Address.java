package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
 class Address {

    @Column(name = "miasto")
    private String city;
    @Column(name = "ulica")
    private String street;
    @Column(name = "kod")
    private String zip;

}
