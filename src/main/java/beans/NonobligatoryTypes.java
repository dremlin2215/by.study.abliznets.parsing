package beans;

public enum NonobligatoryTypes {
    DISPLAY("display"), CASE("case"), DRIVE("drive"), STORAGE("storage"), SOUNDCARD("soundcard"), VIDEOCARD("videocard"),
    WEBCAMERA("webcamera"), MOUSE("mouse"), JOYSTICK("joystick"), KEYBOARD("keyboard"), PLOTTER("plotter"), SCANNER("scanner");
    String value;
    NonobligatoryTypes(String type) {

    }

    public String getValue() {
        return value;
    }
}
