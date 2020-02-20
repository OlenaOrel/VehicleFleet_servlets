package ua.training.web.conctant;

public interface WebConstants {

    String LANG_ATTRIBUTE = "lang";
    String EMAIL_ATTRIBUTE = "email";
    String PASS_ATTRIBUTE = "pass";
    String ROLE_ATTRIBUTE = "role";
    String LOGGED_USERS_ATTRIBUTE = "loggedUsers";

    String ROUTE_ID_ATTRIBUTE = "routeId";
    String ROUTE_LIST_ATTRIBUTE = "routeList";
    String ROUTE_NUMBER_ATTRIBUTE = "routeNumber";
    String EMPTY_ROUTE_LIST_ATTRIBUTE = "isRouteListEmpty";
    String BUS_ID_ATTRIBUTE = "busId";
    String BUS_LIST_ATTRIBUTE = "busList";
    String EMPTY_BUS_LIST_ATTRIBUTE = "isBusListEmpty";
    String DRIVER_ID_ATTRIBUTE = "driverId";
    String DRIVER_LIST_ATTRIBUTE = "driverList";
    String EMPTY_DRIVER_LIST_ATTRIBUTE = "isDriverListEmpty";
    String APPOINT_DTO_ATTRIBUTE = "appointDto";
    String CONFIRM_ATTRIBUTE = "confirm";
    String PASS_NOT_CONFIRM_ATTRIBUTE = "passNotConfirm";
    String INVALID_INPUT_ATTRIBUTE = "invalidInput";
    String ERROR_ATTRIBUTE = "error";
    String APPOINT_PRESENT_ATTRIBUTE = "appointPresent";
    String APPOINTMENT_DTO_LIST_ATTRIBUTE = "appointmentDtoList";
    String APPOINTMENT_STATUS_ATTRIBUTE = "status";

    String REDIRECT = "redirect:";
    String ROOT_PATH = "/VF";
    String LOGIN_PATH = "/login";
    String REGISTRATION_PATH = "/register";
    String LOGOUT_PATH = "/logout";
    String DENIED_PATH = "/denied";

    String ADMIN_PATH = "/admin";
    String APPOINT_ROUTE_PATH = "/admin/appoint/route";
    String APPOINT_BUS_PATH = "/admin/appoint/bus";
    String APPOINT_DRIVER_PATH = "/admin/appoint/driver";
    String CONFIRM_APPOINT_PATH = "/admin/appoint/confirm";

    String DRIVER_PATH = "/driver";

    String MAIN_PAGE = "/WEB-INF/page/index.jsp";
    String LOGIN_PAGE = "/WEB-INF/page/login.jsp";
    String ERROR_PAGE = "/WEB-INF/page/error.jsp";
    String DENIED_PAGE = "/WEB-INF/page/access_denied.jsp";
    String REGISTER_PAGE = "/WEB-INF/page/register.jsp";

    String DRIVER_PAGE = "/WEB-INF/page/driver/driver_menu.jsp";
    String MAIN_ADMIN_PAGE = "/WEB-INF/page/admin/admin_menu.jsp";
    String APPOINT_ROUTE_PAGE = "/WEB-INF/page/admin/appoint/add_route.jsp";
    String APPOINT_BUS_PAGE = "/WEB-INF/page/admin/appoint/add_bus.jsp";
    String APPOINT_DRIVER_PAGE = "/WEB-INF/page/admin/appoint/add_driver.jsp";
    String CONFIRM_APPOINT_PAGE = "/WEB-INF/page/admin/appoint/confirm.jsp";

}
