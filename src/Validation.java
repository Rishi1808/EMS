public class Validation {

    public static boolean isValidAadhaar(String aadhaar) {
        return aadhaar.matches("^[2-9]{1}[0-9]{11}$");
    }

    public static boolean isValidPAN(String pan) {
        return pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}");
    }

    public static boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public static boolean isValidAge(int age) {
        return age >= 18 && age <= 65;
    }

    public static boolean isValidSalary(double salary) {
        return salary >= 0;
    }

    public static boolean isValidName(String name) {
        return name.matches("[A-Za-z ]+");
    }
}
