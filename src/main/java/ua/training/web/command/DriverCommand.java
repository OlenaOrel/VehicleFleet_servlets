package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dto.AppointDto;
import ua.training.dto.AppointDtoConverter;
import ua.training.entity.Appointment;
import ua.training.service.AppointmentService;
import ua.training.web.conctant.WebConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static ua.training.web.conctant.WebConstants.*;

public class DriverCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DriverCommand.class);
    private AppointmentService appointmentService = new AppointmentService();
    private AppointDtoConverter converter = new AppointDtoConverter();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean isAppointConfirm = Boolean.parseBoolean(request.getParameter(CONFIRM_ATTRIBUTE));
        session.setAttribute(APPOINT_PRESENT_ATTRIBUTE, false);
        String email = (String) session.getServletContext().getAttribute(EMAIL_ATTRIBUTE);
        Optional<Appointment> optionalAppointment = appointmentService.getAppointmentForDriver(email);
        if (optionalAppointment.isPresent()) {
            LOGGER.info("Appointment {}", optionalAppointment.get());
            if (isAppointConfirm) {
                appointmentService.setStatusConfirm(optionalAppointment.get());
                session.setAttribute(CONFIRM_ATTRIBUTE, true);
                return WebConstants.DRIVER_PAGE;
            }
            session.setAttribute(APPOINT_PRESENT_ATTRIBUTE, true);
            AppointDto appointDto = converter.convert(optionalAppointment.get());
            session.setAttribute(APPOINT_DTO_ATTRIBUTE, appointDto);
        }
        return WebConstants.DRIVER_PAGE;
    }
}
