package ua.training.web.command.appoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dto.AppointmentDto;
import ua.training.utility.Page;
import ua.training.utility.PageService;
import ua.training.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AppointmentHistoryCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AppointmentHistoryCommand.class);
    private static final int PAGE_SIZE = 5;
    private PageService pageService = new PageService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String current = request.getParameter("page");
        int currentPage = 1;
        if (current != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        Page<AppointmentDto> page = pageService.getAppointmentPage(currentPage, PAGE_SIZE);
        LOGGER.info(page);
        session.setAttribute("page", page);
        return "/WEB-INF/page/admin/appoint/history.jsp";
    }
}
