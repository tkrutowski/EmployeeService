package net.focik.hr.employee.infrastructure.dto;

import lombok.*;
import net.focik.hr.employee.domain.worktimerecords.share.IllnessType;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "choroba")
@Getter
@ToString
@Builder
public class IllnessDto {

    @EmbeddedId
    private IllnessId illnessId;
    @Column(name = "id_rodzaj_choroby")
    @Convert(converter = IllnessTypeConverter.class)
    private IllnessType illnessType;
}

class IllnessTypeConverter implements AttributeConverter<IllnessType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(IllnessType illnessType) {
        return illnessType.getIdValue();
    }

    @Override
    public IllnessType convertToEntityAttribute(Integer idValue) {
        return IllnessType.fromIdValue(idValue);
    }

}