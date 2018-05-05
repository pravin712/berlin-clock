package com.ubs.opsit.interviews.helper;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class BerlinClockHelper {

    public static final String OFF = "O";
    public static final String YELLOW = "Y";
    public static final String RED = "R";
    public static final String NEW_LINE = "\r\n";

    public static String getMinutesString(int minutes) {
        StringBuilder sb = new StringBuilder();
        int quarterBlocks = minutes / 15;
        int aboveQuarters = minutes % 15;
        int fiveMinuteBlocks = aboveQuarters / 5;
        int bottomMinutes = aboveQuarters % 5;

        for (int i = 0; i < quarterBlocks; i++) {
            //YYR
            sb.append(YELLOW).append(YELLOW).append(RED);
        }
        for (int i = 0; i < fiveMinuteBlocks; i++) {
            //Y
            sb.append(YELLOW);
        }
        for (int i = 0; i < 11 - (3 * quarterBlocks + fiveMinuteBlocks); i++) {
            //O
            sb.append(OFF);
        }
        sb.append(NEW_LINE);
        for (int i = 0; i < bottomMinutes; i++) {
            //Y
            sb.append(YELLOW);
        }
        for (int i = 0; i < 4 - bottomMinutes; i++) {
            //O
            sb.append(OFF);
        }
        return sb.toString();
    }

    public static String getHoursString(int hours) {
        StringBuilder sb = new StringBuilder();
        int topHours = hours / 5;
        int bottomHours = hours % 5;
        for (int i = 0; i < topHours; i++) {
            sb.append(RED);
        }
        for (int i = 0; i < 4 - topHours; i++) {
            sb.append(OFF);
        }
        sb.append(NEW_LINE);

        for (int i = 0; i < bottomHours; i++) {
            sb.append(RED);
        }
        for (int i = 0; i < 4 - bottomHours; i++) {
            sb.append(OFF);
        }
        return sb.toString();
    }

    public static String getSecondsString(int seconds) {
        if (seconds % 2 == 0) {
            return YELLOW;
        }
        return OFF;
    }

    public static String[] splitAndValidateTimeString(String timeInput) {
        if (StringUtils.isNotBlank(timeInput)) {
            timeInput = timeInput.trim();
            String[] splits = timeInput.split(":");
            if (isValidTimeInput(splits)) {
                return splits;
            }
        }
        return null;
    }

    public static boolean isValidTimeInput(String[] splits) {
        boolean isValid = false;
        if (splits.length == 3) { //input had exactly two colons
            String hours = splits[0];
            String minutes = splits[1];
            String seconds = splits[2];
            if (isValidMinutesOrSeconds(minutes) && isValidMinutesOrSeconds(seconds)) {

                isValid = isValidHours(hours, Integer.parseInt(minutes), Integer.parseInt(seconds));
            }
        }
        return isValid;
    }

    public static boolean isValidHours(String hours, int minutes, int seconds) {
        boolean isValid = false;
        if (NumberUtils.isDigits(hours) && hours.length() == 2) {
            int intHours = Integer.parseInt(hours);
            if ((intHours == 24) && (minutes == 0 && seconds == 0)) {
                isValid = true;
            } else {
                isValid = intHours >= 0 && intHours <= 23;
            }
        }
        return isValid;
    }

    public static boolean isValidMinutesOrSeconds(String minutesOrSeconds) {
        boolean isValid = false;
        if (NumberUtils.isDigits(minutesOrSeconds) && minutesOrSeconds.length() == 2) {
            int intMinutesOrSeconds = Integer.parseInt(minutesOrSeconds);
            isValid = intMinutesOrSeconds >= 0 && intMinutesOrSeconds <= 59;
        }
        return isValid;
    }
}
