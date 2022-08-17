package net.focik.hr.employee.domain.share;

import net.focik.hr.employee.domain.workTimeRecords.share.IllnessType;

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


//    public static IllnessType fromDbValue(String dbValue) {
//        switch (dbValue) {
//            case "chorobowy 80%":
//                return ILLNESS_80;
//            case "chorobowy 100%":
//                return ILLNESS_100;
//            case "wypadkowy":
//                return INCIDENTIAL;
//            case "macierzyński":
//                return MOTHERLY;
//            case "opiekuńczy":
//                return CARE;
//            case "postojowy 80%":
//                return DOWN_TIME;
//            default:
//                throw new IllegalArgumentException("Value [" + dbValue
//                        + "] not supported.");
//        }
//    }
//
//    public static IllnessType fromIdValue(Integer idValue) {
//        switch (idValue) {
//            case 1:
//                return ILLNESS_80;
//            case 2:
//                return ILLNESS_100;
//            case 3:
//                return INCIDENTIAL;
//            case 4:
//                return MOTHERLY;
//            case 5:
//                return CARE;
//            case 6:
//                return DOWN_TIME;
//            default:
//                throw new IllegalArgumentException("Value [" + idValue
//                        + "] not supported.");
//        }
//    }

}
