package net.focik.hr.employee.infrastructure.dto;

import lombok.*;
import net.focik.hr.employee.domain.worktimerecords.share.DayOffType;

import javax.persistence.*;

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
    @Column(name = "id_rodzaj_urlopu")
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