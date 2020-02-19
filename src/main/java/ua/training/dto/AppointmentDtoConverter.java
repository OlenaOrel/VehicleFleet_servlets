package ua.training.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.entity.Appointment;
import ua.training.entity.Bus;
import ua.training.entity.Route;
import ua.training.entity.User;
import ua.training.exception.EntityNotFoundException;
import ua.training.service.BusService;
import ua.training.service.RouteService;
import ua.training.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class AppointmentDtoConverter {

    private static final Logger LOGGER = LogManager.getLogger(AppointmentDtoConverter.class);
    UserService userService = new UserService();
    BusService busService = new BusService();
    RouteService routeService = new RouteService();

    public List<AppointmentDto> covertAllToDto(List<Appointment> appointments) {
        return appointments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto convertToDto(Appointment appointment) {
        AppointmentDto result = new AppointmentDto();
        try {
            User user = getDriver(appointment);
            setUserParameters(result, user);
            Route route = getRoute(appointment);
            setRouteParameters(result, route);
            Bus bus = getBus(appointment);
            setBusParameters(result, bus);
            result.setDate(appointment.getDate());
            result.setStatus(appointment.getStatus());
        } catch (EntityNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    private User getDriver(Appointment appointment) throws EntityNotFoundException {
        return userService
                .getUserById(appointment.getDriverId())
                .orElseThrow(
                        () -> new EntityNotFoundException("User with id = " +
                                appointment.getDriverId() + " not found"));
    }

    private void setUserParameters(AppointmentDto appointmentDto, User user) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        String fullNameUk = user.getOriginFirstName() + " " + user.getOriginLastName();
        appointmentDto.setDriverFullName(fullName);
        appointmentDto.setDriverFullNameUk(fullNameUk);
    }

    private Route getRoute(Appointment appointment) throws EntityNotFoundException {
        return routeService
                .getRouteById(appointment.getRouteId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Route with id = " +
                                appointment.getRouteId() + " not found"));
    }

    private void setRouteParameters(AppointmentDto appointmentDto, Route route) {
        appointmentDto.setRouteNumber(route.getNumber());
        appointmentDto.setRouteDeparture(route.getDepartureFromCityEn());
        appointmentDto.setRouteDepartureUk(route.getDepartureFromCityUk());
        appointmentDto.setRouteArrival(route.getArrivalToCityEn());
        appointmentDto.setRouteArrivalUk(route.getArrivalToCityUk());
    }

    private Bus getBus(Appointment appointment) throws EntityNotFoundException {
        return busService
                .getBusById(appointment.getBusId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Bus with id = " +
                                appointment.getBusId() + " not found"));
    }

    private void setBusParameters(AppointmentDto appointmentDto, Bus bus) {
        appointmentDto.setBusMark(bus.getMark());
        appointmentDto.setBusLicensePlate(bus.getLicensePlate());
    }
}
