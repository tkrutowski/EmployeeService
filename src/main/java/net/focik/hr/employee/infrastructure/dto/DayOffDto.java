package net.focik.hr.employee.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.focik.hr.employee.domain.workTimeRecords.share.DayOffType;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "urlop")
@Getter
@ToString
@Builder
public class DayOffDto {

    @EmbeddedId
    private DayOffId dayOffId;
    @Column(name = "id_rodzaj_choroby")
    @Convert(converter = DayOffTypeConverter.class)
    private DayOffType dayOffType;
}

class DayOffTypeConverter implements AttributeConverter<DayOffType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DayOffType dayOffType) {
        return dayOffType.getIdValue();
    }

    @Override
    public DayOffType convertToEntityAttribute(Integer idValue) {
        return DayOffType.fromIdValue(idValue);
    }

}