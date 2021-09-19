package net.focik.hr.employee.domain;

public interface IEmployee {
    void hire();
    void dismiss();
    void changeAddress(Address address);
    void subtractDayOff(float numberOfDays);

}
