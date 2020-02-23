package ua.training.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.dao.BusDao;
import ua.training.entity.Bus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceTest {

    private static final int BUS_ID = 0;
    @InjectMocks
    private BusService instance;

    @Mock
    private Bus bus;

    @Mock
    private BusDao dao;


    @Test
    public void shouldReturnBusWhenBusFound() {
        when(dao.findById(BUS_ID)).thenReturn(Optional.of(bus));

        Optional<Bus> result = instance.getBusById(BUS_ID);

        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo(Optional.of(bus));
    }

    @Test
    public void shouldReturnEmptyWhenRouteNotFound() {
        Optional<Bus> result = instance.getBusById(BUS_ID);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnRouteListWhenNotAppointRouteFound() {
        when(dao.findNotAppointBus(any(LocalDate.class))).thenReturn(Collections.singletonList(bus));

        List<Bus> result = instance.getNotAppointBus();

        assertThat(result).isNotEmpty();
        assertThat(result).contains(bus);
    }

    @Test
    public void shouldReturnEmptyListWhenAllRoutesAppointed() {
        List<Bus> result = instance.getNotAppointBus();

        assertThat(result).isEmpty();
    }

}
