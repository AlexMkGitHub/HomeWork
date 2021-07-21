package hw1;

public enum DayOfWeekMain {
    Monday(true),
    Tuesday(true),
    Wednesday(true),
    Thursday(true),
    Friday(true),
    Saturday(false),
    Sunday(false);

    DayOfWeekMain(boolean working) {
    }

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static String getWorkingHours(DayOfWeekMain day) {
        int d;
        if (day.ordinal() == 5) {
            return (ANSI_RED + "СУББОТА это ВЫХОДНОЙ день!!!" + ANSI_RESET);
        }
        if (day.ordinal() == 6) {
            return (ANSI_RED + "ВОСКРЕСЕНЬЕ это ВЫХОДНОЙ день!!!" + ANSI_RESET);
        } else {
            d = (5 - day.ordinal()) * 24;
        }
        return (ANSI_GREEN + "До конца рабочей недели осталось " + d + " часов!" + ANSI_RESET);
    }
}



