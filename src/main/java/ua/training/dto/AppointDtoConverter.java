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

import java.util.Optional;

public class AppointDtoConverter {

    private static final Logger LOGGER = LogManager.getLogger(AppointDtoConverter.class);
    UserService userService = new UserService();
    BusService busService = new BusService();
    RouteService routeService = new RouteService();

    public AppointDto convert(Appointment appointment) {
        AppointDto result = new AppointDto();
        try {
            User user = getDriver(appointment);
            setUserParameters(result, user);
            Route route = getRoute(appointment);
            setRouteParameters(result, route);
            Bus bus = getBus(appointment);
            setBusParameters(result, bus);
        }catch (EntityNotFoundException e) {
            LOGGER.error(e);
        }
        return result;
    }

    private User getDriver(Appointment appointment) throws EntityNotFoundException {
        int driverId = appointment.getDriverId();
        Optional<User> user = userService.getUserById(driverId);
        if (user.isPresent()) {
            User driver = user.get();
            return driver;
        }
        throw new EntityNotFoundException("User with id = " + driverId + " not found");
    }

    private void setUserParameters(AppointDto appointDto, User user) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        String fullNameUk = user.getOriginFirstName() + " " + user.getOriginLastName();
        appointDto.setDriverFullName(fullName);
        appointDto.setDriverFullNameUk(fullNameUk);
    }

    private Route getRoute(Appointment appointment) throws EntityNotFoundException {
        int routeId = appointment.getRouteId();
        Optional<Route> route = routeService.getRouteById(routeId);
        if (route.isPresent()) {
            return route.get();
        }
        throw new EntityNotFoundException("Route with id = " + routeId + " not found");
    }

    private void setRouteParameters(AppointDto appointDto, Route route) {
       appointDto.setRouteNumber(route.getNumber());
       appointDto.setRouteDeparture(route.getDepartureFromCityEn());
       appointDto.setRouteDepartureUk(route.getDepartureFromCityUk());
       appointDto.setRouteArrival(route.getArrivalToCityEn());
       appointDto.setRouteArrivalUk(route.getArrivalToCityUk());
    }

    private Bus getBus(Appointment appointment) throws EntityNotFoundException {
        int busId = appointment.getBusId();
        Optional<Bus> bus = busService.getBusById(busId);
        if (bus.isPresent()) {
            return bus.get();
        }
        throw new EntityNotFoundException("Bus with id = " + busId + " not found");
    }

    private void setBusParameters(AppointDto appointDto, Bus bus) {
        appointDto.setBusMark(bus.getMark());
        appointDto.setBusLicensePlate(bus.getLicensePlate());
    }
}
