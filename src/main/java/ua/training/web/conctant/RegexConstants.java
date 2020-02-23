package ua.training.web.conctant;

public interface RegexConstants {

    String NAME_UK = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']{1,20}$";

    String NAME_EN = "^[A-Z][a-z]{1,20}$";

    String EMAIL = "^(|(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)" +
            "|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6})$";

}
