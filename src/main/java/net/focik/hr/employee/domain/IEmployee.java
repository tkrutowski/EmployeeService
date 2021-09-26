package net.focik.hr.employee.domain;

 interface IEmployee {
    void hire();
    void dismiss();
    void changeAddress(Address address);
    void subtractDayOff(float numberOfDays);

}
