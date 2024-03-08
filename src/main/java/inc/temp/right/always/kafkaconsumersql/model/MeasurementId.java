package inc.temp.right.always.kafkaconsumersql.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MeasurementId implements Serializable {
    @Column(name = "thermometerid")
    private String thermometerId;
    @Column(name = "measuredat")
    private Timestamp measuredAt;
}

