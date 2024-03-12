package inc.temp.right.always.kafkaconsumersql;

import inc.temp.right.always.kafkaconsumersql.listeners.KafkaConsumerListener;
import inc.temp.right.always.kafkaconsumersql.model.MeasurementId;
import inc.temp.right.always.kafkaconsumersql.model.TempMeasurement;
import inc.temp.right.always.kafkaconsumersql.repositories.TempMeasurementsRepository;
import inc.temp.right.always.kafkaconsumersql.services.TempMeasurementsService;
import inc.temp.right.always.temperaturemodel.TemperatureMeasurement;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

class KafkaConsumerSqlApplicationTests {
	@Test
	void KafkaConsumerListener_handleTemperatureMeasurement_Test() {
		KafkaConsumerListener kafkaConsumerListener = new KafkaConsumerListener();
		TempMeasurementsService tempMeasurementsService = mock(TempMeasurementsService.class);

		TemperatureMeasurement temperatureMeasurement = spy(new TemperatureMeasurement("room-1", "thermometer-1", Timestamp.valueOf("2024-02-01 00:00:01").toInstant().toEpochMilli(), 20.0));
		TempMeasurement tempMeasure = new TempMeasurement(new MeasurementId("thermometer-1", Timestamp.valueOf("2024-02-01 00:00:01")), "room-1", 20.0);

		doReturn(tempMeasure).when(temperatureMeasurement).toTempMeasurement();
		doReturn(tempMeasure).when(tempMeasurementsService).save(tempMeasure);

		kafkaConsumerListener.setTempMeasurementsService(tempMeasurementsService);

		kafkaConsumerListener.handleTemperatureMeasurement(temperatureMeasurement);

		verify(temperatureMeasurement, times(1)).toTempMeasurement();
		verify(tempMeasurementsService, times(1)).save(tempMeasure);
	}

	@Test
	void TempMeasurementsService_save_Test() {
		TempMeasurementsService tempMeasurementsService = new TempMeasurementsService();
		TempMeasurementsRepository tempMeasurementsRepository = mock(TempMeasurementsRepository.class);

		TempMeasurement tempMeasure = new TempMeasurement(new MeasurementId("thermometer-1", Timestamp.valueOf("2024-02-01 00:00:01")), "room-1", 20.0);

		doReturn(tempMeasure).when(tempMeasurementsRepository).save(tempMeasure);

		tempMeasurementsService.setTempMeasurementsRepository(tempMeasurementsRepository);

		TempMeasurement result = tempMeasurementsService.save(tempMeasure);

		verify(tempMeasurementsRepository, times(1)).save(tempMeasure);
		assertEquals(tempMeasure, result, String.format("Expected result: %s and returned result: %s are not the same when saving anomaly.", tempMeasure, result));
	}

	@Test
	void TemperatureMeasurement_toTempMeasurement_Test() {
		TemperatureMeasurement temperatureMeasurement = new TemperatureMeasurement("room-1", "thermometer-1", Timestamp.valueOf("2024-02-01 00:00:01").toInstant().toEpochMilli(), 20.0);
		TempMeasurement tempMeasure = new TempMeasurement(new MeasurementId("thermometer-1", Timestamp.valueOf("2024-02-01 00:00:01")), "room-1", 20.0);

		TempMeasurement result = temperatureMeasurement.toTempMeasurement();

		assertEquals(tempMeasure, result, String.format("Expected result: %s and returned result: %s are not the same when saving anomaly.", tempMeasure, result));
	}
}
