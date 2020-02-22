package ua.training.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.dao.RouteDao;
import ua.training.entity.Route;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceTest {

    private static int ROUTE_ID = 0;

    @InjectMocks
    private RouteService instance;

    @Mock
    private RouteDao dao;

    @Mock
    private Route route;

    @Test
    public void shouldReturnRouteWhenRouteFound() {
        when(dao.findById(ROUTE_ID)).thenReturn(Optional.of(route));

        Optional<Route> result = instance.getRouteById(ROUTE_ID);

        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo(Optional.of(route));
    }

    @Test
    public void shouldReturnEmptyWhenRouteNotFound() {
        Optional<Route> result = instance.getRouteById(ROUTE_ID);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnRouteListWhenNotAppointRouteFound() {
        when(dao.findNotAppointRoutes(any(LocalDate.class))).thenReturn(Collections.singletonList(route));

        List<Route> result = instance.getNotAppointRoute();

        assertThat(result).isNotEmpty();
        assertThat(result).contains(route);
    }

    @Test
    public void shouldReturnEmptyListWhenAllRoutesAppointed() {
        List<Route> result = instance.getNotAppointRoute();

        assertThat(result).isEmpty();
    }

}
