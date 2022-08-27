package net.focik.hr.employee.domain.share;

public enum EmployeeType {
    WORKER("Pracownik terenowy"),
    CONSTRUCTION_MANAGER("Kierownik budowy"),
    OFFICE_WORKER("Pracownik biurowy"),
    OVERSEER("Brygadzista");

    private String viewValue;

    EmployeeType(String viewValue) {
        this.viewValue = viewValue;
    }

    public String getViewValue() {
        return viewValue;
    }

}
