package ua.training.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.dao.AppointmentDao;
import ua.training.entity.Appointment;
import ua.training.entity.AppointmentStatus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceTest {

    private static final int APPOINTMENT_ID = 0;
    private static final int ROUTE_ID = 0;
    private static final int BUS_ID = 0;
    private static final int DRIVER_ID = 0;


    @InjectMocks
    private AppointmentService instance;

    @Mock
    private Appointment appointment;

    @Mock
    private AppointmentDao dao;

    @Test
    public void shouldReturnAppointmentWhenCreateAppointment() {
        Appointment result = instance.createAppointment(ROUTE_ID, BUS_ID, DRIVER_ID);

        assertThat(result.getRouteId()).isEqualTo(ROUTE_ID);
        assertThat(result.getBusId()).isEqualTo(BUS_ID);
        assertThat(result.getDriverId()).isEqualTo(DRIVER_ID);
    }

    @Test
    public void shouldSaveAppointment() {
        instance.save(appointment);

        verify(dao).save(appointment);
    }

    @Test
    public void shouldReturnAppointmentWhenGetAppointmentForDriver() {
        when(dao.findAppointmentForDriver(LocalDate.now(), DRIVER_ID)).thenReturn(Optional.of(appointment));

        Optional<Appointment> result = instance.getAppointmentForDriver(DRIVER_ID);

        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo(Optional.of(appointment));
    }

    @Test
    public void shouldReturnEmptyWhenRouteNotFound() {
        Optional<Appointment> result = instance.getAppointmentForDriver(DRIVER_ID);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldSetStatusConfirm() {
        instance.setStatusConfirm(APPOINTMENT_ID);

        verify(dao).updateStatusByAppointmentId(AppointmentStatus.CONFIRMED, APPOINTMENT_ID);
    }

    @Test
    public void shouldReturnAppointmentListWhenNotFinishedAppointmentExist() {
        when(dao.findNotFinishedAppointment()).thenReturn(Collections.singletonList(appointment));

        List<Appointment> result = instance.getNotFinishedAppointment();

        assertThat(result).isNotEmpty();
        assertThat(result).contains(appointment);
    }

    @Test
    public void shouldSetStatusFinished() {
        instance.setStatusFinished(APPOINTMENT_ID);

        verify(dao).updateStatusByAppointmentId(AppointmentStatus.FINISHED, APPOINTMENT_ID);
    }
}
