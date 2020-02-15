package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dto.AppointmentDto;
import ua.training.dto.AppointmentDtoConverter;
import ua.training.entity.Appointment;
import ua.training.entity.AppointmentStatus;
import ua.training.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static ua.training.web.conctant.WebConstants.*;

public class AdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AdminCommand.class);

    private AppointmentService service = new AppointmentService();
    private AppointmentDtoConverter converter = new AppointmentDtoConverter();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String routeNumber = request.getParameter(ROUTE_NUMBER_ATTRIBUTE);
        String status = request.getParameter(APPOINTMENT_STATUS_ATTRIBUTE);
        if (routeNumber != null && status != null) {
            LOGGER.info("number = {}, status = {}", routeNumber, status);
            service.doFinish(Integer.parseInt(routeNumber), AppointmentStatus.valueOf(status));
            LOGGER.info("Appointment with route number {} finished", routeNumber);
            return REDIRECT + ROOT_PATH + ADMIN_PATH;
        }
        List<Appointment> notFinishedAppointments = service.getNotFinishedAppointment();
        List<AppointmentDto> appointmentDtoList = converter.covertAllToDto(notFinishedAppointments);
        session.setAttribute(APPOINTMENT_DTO_LIST_ATTRIBUTE, appointmentDtoList);
        return MAIN_ADMIN_PAGE;
    }
}
