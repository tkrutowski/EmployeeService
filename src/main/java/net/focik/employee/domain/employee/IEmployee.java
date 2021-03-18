package net.focik.employee.domain.employee;

public interface IEmployee {
    void hire();
    void dismiss();
    void changeAddress(Address address);
    void subtractDayOff(float numberOfDays);

}
