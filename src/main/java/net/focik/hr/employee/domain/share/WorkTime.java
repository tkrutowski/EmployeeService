package net.focik.hr.employee.domain.share;

public enum WorkTime {
    FULL_TIME("pełny etat"),
    PART_TIME("pół etatu");

    private String viewValue;

    WorkTime(String viewValue) {
        this.viewValue = viewValue;
    }


    public String getViewValue() {
        return viewValue;
    }

}
