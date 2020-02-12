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

public class ConfirmAppointCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ConfirmAppointCommand.class);
    private AppointmentService appointmentService = new AppointmentService();
    private AppointDtoConverter converter = new AppointDtoConverter();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String routeId = (String) session.getAttribute("routeId");
        String busId = (String) session.getAttribute("busId");
        String driverId = (String) session.getAttribute("driverId");
        String confirm = request.getParameter("confirm");
        LOGGER.info("Confirmed: {}", confirm);
        int idRoute = Integer.parseInt(routeId);
        int idBus = Integer.parseInt(busId);
        int idDriver = Integer.parseInt(driverId);
        Appointment appointment = appointmentService.createAppointment(idRoute, idBus, idDriver);
        AppointDto appointDto = converter.convert(appointment);
        session.setAttribute("appointDto", appointDto);
        if (confirm != null && Boolean.parseBoolean(confirm)) {
            LOGGER.info("Appointment confirmed {}", appointment);
            appointmentService.save(appointment);
            return "redirect:/VF/admin";
        }
        return "/WEB-INF/admin/appoint/confirm.jsp";
    }
}
