package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dto.AppointmentDto;
import ua.training.dto.AppointmentDtoConverter;
import ua.training.entity.Appointment;
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
        String appointmentId = request.getParameter(APPOINTMENT_ID_ATTRIBUTE);
        if (appointmentId != null) {
            service.setStatusFinished(Integer.parseInt(appointmentId));
            LOGGER.info("Appointment with id = {} finished", appointmentId);
            return REDIRECT + ROOT_PATH + ADMIN_PATH;
        }
        List<Appointment> notFinishedAppointments = service.getNotFinishedAppointment();
        List<AppointmentDto> appointmentDtoList = converter.covertAllToDto(notFinishedAppointments);
        session.setAttribute(APPOINTMENT_DTO_LIST_ATTRIBUTE, appointmentDtoList);
        return MAIN_ADMIN_PAGE;
    }
}
