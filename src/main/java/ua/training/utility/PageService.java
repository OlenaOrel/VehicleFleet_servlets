package ua.training.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.AppointmentDao;
import ua.training.dao.DaoFactory;
import ua.training.dto.AppointmentDto;
import ua.training.dto.AppointmentDtoConverter;
import ua.training.entity.Appointment;

import java.util.List;

public class PageService {

    private final Logger LOGGER = LogManager.getLogger(PageService.class);

    private AppointmentDtoConverter converter = new AppointmentDtoConverter();

    public Page<AppointmentDto> getAppointmentPage(int currentPage, int pageSize) {
        AppointmentDao dao = DaoFactory.getInstance().createAppointmentDao();
        int daoCurrentPage = currentPage - 1;
        int totalElements = dao.countAppointments();
        LOGGER.info("Total elements: {}", totalElements);
        int offset = pageSize * daoCurrentPage;
        List<AppointmentDto> dtoElements = getAppointmentsForPage(offset, pageSize);
        return new Page<>(totalElements, daoCurrentPage, dtoElements, pageSize);
    }

    private List<AppointmentDto> getAppointmentsForPage(int offset, int pageSize) {
        AppointmentDao dao = DaoFactory.getInstance().createAppointmentDao();
        List<Appointment> elements = dao.findAllAppointments(offset, pageSize);
        LOGGER.info(elements);
        return converter.covertAllToDto(elements);
    }

}
