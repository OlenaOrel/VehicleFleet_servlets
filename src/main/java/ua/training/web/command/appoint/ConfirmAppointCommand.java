package ua.training.web.command.appoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dto.AppointmentDto;
import ua.training.dto.AppointmentDtoConverter;
import ua.training.entity.Appointment;
import ua.training.service.AppointmentService;
import ua.training.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.training.web.conctant.WebConstants.*;

public class ConfirmAppointCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ConfirmAppointCommand.class);

    private AppointmentService appointmentService;
    private AppointmentDtoConverter converter;

    public ConfirmAppointCommand() {
        appointmentService = new AppointmentService();
        converter = new AppointmentDtoConverter();
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String confirm = request.getParameter(CONFIRM_ATTRIBUTE);
        LOGGER.info("Confirmed: {}", confirm);
        Appointment appointment = createAppointment(session);
        if (Boolean.parseBoolean(confirm)) {
            LOGGER.info("Appointment confirmed {}", appointment);
            appointmentService.save(appointment);
            removeAttributeFromSession(session);
            return REDIRECT + ROOT_PATH + ADMIN_PATH;
        }
        AppointmentDto appointmentDto = converter.convertToDto(appointment);
        session.setAttribute(APPOINT_DTO_ATTRIBUTE, appointmentDto);
        return CONFIRM_APPOINT_PAGE;
    }

    private Appointment createAppointment(HttpSession session) {
        String routeId = (String) session.getAttribute(ROUTE_ID_ATTRIBUTE);
        String busId = (String) session.getAttribute(BUS_ID_ATTRIBUTE);
        String driverId = (String) session.getAttribute(DRIVER_ID_ATTRIBUTE);
        int idRoute = Integer.parseInt(routeId);
        int idBus = Integer.parseInt(busId);
        int idDriver = Integer.parseInt(driverId);
        return appointmentService.createAppointment(idRoute, idBus, idDriver);
    }

    private void removeAttributeFromSession(HttpSession session) {
        session.removeAttribute(ROUTE_ID_ATTRIBUTE);
        session.removeAttribute(BUS_ID_ATTRIBUTE);
        session.removeAttribute(DRIVER_ID_ATTRIBUTE);
        session.removeAttribute(APPOINT_DTO_ATTRIBUTE);
        session.removeAttribute(CONFIRM_ATTRIBUTE);
        session.removeAttribute(ROUTE_LIST_ATTRIBUTE);
        session.removeAttribute(BUS_LIST_ATTRIBUTE);
        session.removeAttribute(DRIVER_LIST_ATTRIBUTE);
        session.removeAttribute(EMPTY_ROUTE_LIST_ATTRIBUTE);
        session.removeAttribute(EMPTY_BUS_LIST_ATTRIBUTE);
        session.removeAttribute(EMPTY_DRIVER_LIST_ATTRIBUTE);
    }
}
