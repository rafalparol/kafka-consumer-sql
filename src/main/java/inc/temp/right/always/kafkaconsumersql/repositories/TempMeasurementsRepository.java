package inc.temp.right.always.kafkaconsumersql.repositories;

import inc.temp.right.always.kafkaconsumersql.model.MeasurementId;
import inc.temp.right.always.kafkaconsumersql.model.TempMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempMeasurementsRepository extends JpaRepository<TempMeasurement, MeasurementId> {

}
