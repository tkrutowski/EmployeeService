package net.focik.hr.employee.domain.worktimerecords.share;


public enum DayOffType {

    HALF_DAY("1/2 dnia", 1, 1),
    REST("wypoczynkowy", 2, 1),
    FREE("bezpłatny", 3, 0),
    MOTHERLY("macierzyński", 4, 1),
    EDUCATIONAL("wychowawczy", 5, 0),
    CARE("opieka", 6, 1),
    FATHERLY("ojcowski", 7, 1),
    OCCASIONAL("okolicznościowy", 8, 1);

    private final String dbValue;
    private final Integer id;
    private final Integer percent;

    DayOffType(String dbValue, int id, int percent) {
        this.dbValue = dbValue;
        this.id = id;
        this.percent = percent;
    }

    public String getDbValue() {
        return dbValue;
    }


    public Integer getPercent() {
        return percent;
    }

    public Integer getIdValue() {
        return id;
    }

    public static DayOffType fromDbValue(String dbValue) {
        switch (dbValue) {
            case "1/2 dnia":
                return HALF_DAY;
            case "wypoczynkowy":
                return REST;
            case "bezpłatny":
                return FREE;
            case "macierzyński":
                return MOTHERLY;
            case "wychowawczy":
                return EDUCATIONAL;
            case "opieka":
                return CARE;
            case "ojcowski":
                return FATHERLY;
            case "okolicznościowy":
                return OCCASIONAL;
            default:
                throw new IllegalArgumentException("Value [" + dbValue
                        + "] not supported.");
        }
    }

    public static DayOffType fromIdValue(Integer idValue) {
        switch (idValue) {
            case 1:
                return HALF_DAY;
            case 2:
                return REST;
            case 3:
                return FREE;
            case 4:
                return MOTHERLY;
            case 5:
                return EDUCATIONAL;
            case 6:
                return CARE;
            case 7:
                return FATHERLY;
            case 8:
                return OCCASIONAL;
            default:
                throw new IllegalArgumentException("Value [" + idValue
                        + "] not supported.");
        }
    }
}
