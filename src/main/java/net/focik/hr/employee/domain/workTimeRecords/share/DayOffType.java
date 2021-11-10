package net.focik.hr.employee.domain.workTimeRecords.share;


//TODO jak się da to zmienić na PL
public enum DayOffType {

    HALF_DAY("1/2 dnia", 1,1),
    REST("wypoczynkowy", 2,1),
    FREE("bezpłatny", 3,0),
    MOTHERLY("macierzyński", 4,1),
    EDUCAIONAL("wychowawczy", 5,0),
    CARE("opieka", 6,1),
    FATHERLY("ojcowski", 7,1),
    OCCASIONAL("okolicznościowy", 8,1);

    private String dbValue;
    private Integer id;
    private Integer percent;

    DayOffType(String dbValue, int id, int percent) {
        this.dbValue = dbValue;
        this.id = id;
        this.percent = percent;
    }


    public String getDbValue() {
        return dbValue;
    }

    public void setDbValue(String dbValue) {
        this.dbValue = dbValue;
    }

    public Integer getPercent(){
        return percent;
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
                return EDUCAIONAL;
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
}
