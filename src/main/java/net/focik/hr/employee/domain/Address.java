package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public
class Address {

    @Column(name = "miasto")
    private String city;
    @Column(name = "ulica")
    private String street;
    @Column(name = "kod")
    private String zip;

    @Override
    public String toString() {
        return "Address{" +
                ", zip='" + zip + '\'' +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
