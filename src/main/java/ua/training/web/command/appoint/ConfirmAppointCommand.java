package ua.training.web.command.appoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dto.AppointDto;
import ua.training.dto.AppointDtoConverter;
import ua.training.entity.Appointment;
import ua.training.service.AppointmentService;
import ua.training.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.training.web.conctant.WebConstants.*;

public class ConfirmAppointCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ConfirmAppointCommand.class);
    private AppointmentService appointmentService = new AppointmentService();
    private AppointDtoConverter converter = new AppointDtoConverter();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String routeId = (String) session.getAttribute(ROUTE_ID_ATTRIBUTE);
        String busId = (String) session.getAttribute(BUS_ID_ATTRIBUTE);
        String driverId = (String) session.getAttribute(DRIVER_ID_ATTRIBUTE);
        String confirm = request.getParameter(CONFIRM_ATTRIBUTE);
        LOGGER.info("Confirmed: {}", confirm);
        int idRoute = Integer.parseInt(routeId);
        int idBus = Integer.parseInt(busId);
        int idDriver = Integer.parseInt(driverId);
        Appointment appointment = appointmentService.createAppointment(idRoute, idBus, idDriver);
        AppointDto appointDto = converter.convert(appointment);
        session.setAttribute(APPOINT_DTO_ATTRIBUTE, appointDto);
        if (confirm != null && Boolean.parseBoolean(confirm)) {
            LOGGER.info("Appointment confirmed {}", appointment);
            appointmentService.save(appointment);
            return REDIRECT + ROOT_PATH + ADMIN_PATH;
        }
        return CONFIRM_APPOINT_PAGE;
    }
}
