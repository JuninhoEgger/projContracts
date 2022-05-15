package utils;

public class ValidadeDateUtils {

    private ValidadeDateUtils() {
    }

    public static boolean validateDate(int day, int month, int year) {
        return day > 0 && day < 32 && month > 0 && month < 13 && year > 0 && ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) || ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) || (month == 2 && (day <= 29 && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) || day <= 28));
    }

}
