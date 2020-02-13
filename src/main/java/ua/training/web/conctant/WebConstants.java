package ua.training.web.conctant;

public interface WebConstants {

    String LANG_ATTRIBUTE = "lang";
    String EMAIL_ATTRIBUTE = "email";
    String PASS_ATTRIBUTE = "pass";
    String ROLE_ATTRIBUTE = "role";
    String LOGGED_USERS_ATTRIBUTE = "loggedUsers";

    String ROUTE_ID_ATTRIBUTE = "routeId";
    String ROUTE_LIST_ATTRIBUTE = "routeList";
    String EMPTY_ROUTE_LIST_ATTRIBUTE = "isRouteListEmpty";
    String BUS_ID_ATTRIBUTE = "busId";
    String BUS_LIST_ATTRIBUTE = "busList";
    String EMPTY_BUS_LIST_ATTRIBUTE = "isBusListEmpty";
    String DRIVER_ID_ATTRIBUTE = "driverId";
    String DRIVER_LIST_ATTRIBUTE = "driverList";
    String EMPTY_DRIVER_LIST_ATTRIBUTE = "isDriverListEmpty";
    String APPOINT_DTO_ATTRIBUTE = "appointDto";
    String CONFIRM_ATTRIBUTE = "confirm";


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

    String MAIN_PAGE = "/index.jsp";
    String LOGIN_PAGE = "/login.jsp";
    String ERROR_PAGE = "/WEB-INF/error.jsp";
    String DENIED_PAGE = "/access_denied.jsp";

    String DRIVER_PAGE = "/WEB-INF/driver/driver_menu.jsp";
    String MAIN_ADMIN_PAGE = "/WEB-INF/admin/admin_menu.jsp";
    String APPOINT_ROUTE_PAGE = "/WEB-INF/admin/appoint/add_route.jsp";
    String APPOINT_BUS_PAGE = "/WEB-INF/admin/appoint/add_bus.jsp";
    String APPOINT_DRIVER_PAGE = "/WEB-INF/admin/appoint/add_driver.jsp";
    String CONFIRM_APPOINT_PAGE = "/WEB-INF/admin/appoint/confirm.jsp";

}
