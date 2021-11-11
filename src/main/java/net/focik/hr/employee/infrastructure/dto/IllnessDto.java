package net.focik.hr.employee.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.focik.hr.employee.domain.workTimeRecords.share.IllnessType;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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