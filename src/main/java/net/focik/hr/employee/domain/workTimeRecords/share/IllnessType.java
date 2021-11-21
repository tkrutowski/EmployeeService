package net.focik.hr.employee.domain.workTimeRecords.share;


public enum IllnessType {

    ILLNESS_80("chorobowy 80%", 1,0.8f),
    ILLNESS_100("horobowy 100%", 2,1),
    INCIDENTIAL("wypadkowy", 3,1),
    MOTHERLY("macierzyński", 4,1),
    CARE("opiekuńczy", 5,0.8f),
    DOWN_TIME("postojowy 80%", 6,0.8f);

    private String dbValue;
    private Integer id;
    private Float percent;

    IllnessType(String dbValue, int id, float percent) {
        this.dbValue = dbValue;
        this.id = id;
        this.percent = percent;
    }


    public String getDbValue() {
        return dbValue;
    }
    public Integer getIdValue() {
        return id;
    }

    public float getPercent(){
        return percent;
    }

    public void setDbValue(String dbValue) {
        this.dbValue = dbValue;
    }

    public static IllnessType fromDbValue(String dbValue) {
        switch (dbValue) {
            case "chorobowy 80%":
                return ILLNESS_80;
            case "chorobowy 100%":
                return ILLNESS_100;
            case "wypadkowy":
                return INCIDENTIAL;
            case "macierzyński":
                return MOTHERLY;
            case "opiekuńczy":
                return CARE;
            case "postojowy 80%":
                return DOWN_TIME;
            default:
                throw new IllegalArgumentException("Value [" + dbValue
                        + "] not supported.");
        }
    }

    public static IllnessType fromIdValue(Integer idValue) {
        switch (idValue) {
            case 1:
                return ILLNESS_80;
            case 2:
                return ILLNESS_100;
            case 3:
                return INCIDENTIAL;
            case 4:
                return MOTHERLY;
            case 5:
                return CARE;
            case 6:
                return DOWN_TIME;
            default:
                throw new IllegalArgumentException("Value [" + idValue
                        + "] not supported.");
        }
    }
}
