package inc.temp.right.always.temperaturemodel;

import inc.temp.right.always.kafkaconsumersql.model.MeasurementId;
import inc.temp.right.always.kafkaconsumersql.model.TempMeasurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureMeasurement {
    private String roomId;
    private String thermometerId;
    private long timestamp;
    private double temperature;

    public TempMeasurement toTempMeasurement() {
        return new TempMeasurement(new MeasurementId(this.thermometerId, Timestamp.from(Instant.ofEpochMilli(this.timestamp))), this.roomId, this.temperature);
    }
}
