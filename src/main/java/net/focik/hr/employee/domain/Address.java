package net.focik.hr.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Address {

    private String city;
    private String street;
    private String zip;

    @Override
    public String toString() {
        return "Address{" +
                ", street='" + street + '\'' +
                "zip='" + zip + '\'' +
                ",city='" + city + '\'' +
                '}';
    }
}
