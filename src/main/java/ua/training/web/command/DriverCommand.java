package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dto.AppointmentDto;
import ua.training.dto.AppointmentDtoConverter;
import ua.training.dto.UserDto;
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
    private AppointmentDtoConverter converter = new AppointmentDtoConverter();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String appointmentId = request.getParameter(APPOINTMENT_ID_ATTRIBUTE);
        session.setAttribute(APPOINT_PRESENT_ATTRIBUTE, false);
        UserDto userDto = (UserDto) session.getAttribute(USER_DTO_ATTRIBUTE);
        Optional<Appointment> optionalAppointment = appointmentService.getAppointmentForDriver(userDto.getId());
        if (optionalAppointment.isPresent()) {
            LOGGER.info(optionalAppointment.get());
            if (appointmentId != null) {
                appointmentService.setStatusConfirm(Integer.parseInt(appointmentId));
            }
            AppointmentDto appointmentDto = converter.convertToDto(optionalAppointment.get());
            session.setAttribute(APPOINT_PRESENT_ATTRIBUTE, true);
            session.setAttribute(APPOINT_DTO_ATTRIBUTE, appointmentDto);
        }
        return WebConstants.DRIVER_PAGE;
    }
}
