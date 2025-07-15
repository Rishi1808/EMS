import java.util.regex.Pattern;

public class Validation {
    private static final Pattern AADHAAR_PATTERN = Pattern.compile("^[2-9]{1}[0-9]{11}$");
    private static final Pattern PAN_PATTERN = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]{1}$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]+$");

    public static boolean isValidAadhaar(String aadhaar) {
        return aadhaar != null && AADHAAR_PATTERN.matcher(aadhaar).matches();
    }

    public static boolean isValidPAN(String pan) {
        return pan != null && PAN_PATTERN.matcher(pan).matches();
    }

    public static boolean isValidDate(String date) {
        if (date == null || !DATE_PATTERN.matcher(date).matches()) {
            return false;
        }

        try {
            String[] parts = date.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            if (year < 1900 || year > 2100)
                return false;
            if (month < 1 || month > 12)
                return false;
            if (day < 1 || day > 31)
                return false;

            if (month == 2 && day > 29)
                return false;
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
                return false;

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidAge(int age) {
        return age >= 18 && age <= 65;
    }

    public static boolean isValidSalary(double salary) {
        return salary >= 0 && salary <= 10000000;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() &&
                name.trim().length() >= 2 && name.trim().length() <= 50 &&
                NAME_PATTERN.matcher(name.trim()).matches();
    }

    public static boolean isValidGender(String gender) {
        return gender != null && (gender.equalsIgnoreCase("Male") ||
                gender.equalsIgnoreCase("Female") ||
                gender.equalsIgnoreCase("Other"));
    }

    public static boolean isValidString(String str, int minLength, int maxLength) {
        return str != null && str.trim().length() >= minLength && str.trim().length() <= maxLength;
    }

    public static boolean isAgeAtLeast18AtJoining(String dob, String doj) {
        if (!isValidDate(dob) || !isValidDate(doj)) {
            return false;
        }

        try {
            String[] dobParts = dob.split("-");
            String[] dojParts = doj.split("-");

            int dobYear = Integer.parseInt(dobParts[0]);
            int dobMonth = Integer.parseInt(dobParts[1]);
            int dobDay = Integer.parseInt(dobParts[2]);

            int dojYear = Integer.parseInt(dojParts[0]);
            int dojMonth = Integer.parseInt(dojParts[1]);
            int dojDay = Integer.parseInt(dojParts[2]);

            // Calculate age at joining
            int ageAtJoining = dojYear - dobYear;

            // Adjust if the birthday hasn't occurred yet in the joining year
            if (dojMonth < dobMonth || (dojMonth == dobMonth && dojDay < dobDay)) {
                ageAtJoining--;
            }

            return ageAtJoining >= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
