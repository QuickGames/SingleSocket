package quickgames.socket.single;

public final class Version {

    //region CONSTRUCTOR

    private Version() {
    }

    static {
        _version = "0.0.4";
        _build = 4;
        _date = "07.12.2017";
    }

    //endregion

    //region BUILD

    private static int _build;

    public static int getBuild() {
        return _build;
    }

    //endregion

    //region VERSION

    private static String _version;

    public static String getVersion() {
        return _version;
    }

    public static String getFullVersion() {
        return getVersion() + "." + getBuild();
    }

    //endregion

    //region DATE

    private static String _date;

    public static String getDate() {
        return _date;
    }

    //endregion

    //region GET_VERSION

    public static String get(String pre) {
        return pre + ": " + getFullVersion() + "; " + getDate();
    }

    public static String get() {
        return get("Version");
    }

    //endregion
}