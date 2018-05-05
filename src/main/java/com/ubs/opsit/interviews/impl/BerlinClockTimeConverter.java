package com.ubs.opsit.interviews.impl;

import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviews.helper.BerlinClockHelper;

import static com.ubs.opsit.interviews.helper.BerlinClockHelper.NEW_LINE;

public class BerlinClockTimeConverter implements TimeConverter {


    @Override
    public String convertTime(String timeInput) {
        StringBuilder sb = new StringBuilder();
        //validate and split time string in hours minutes seconds
        String[] splitTime = BerlinClockHelper.splitAndValidateTimeString(timeInput);
        if (splitTime != null) {
            int hours = Integer.parseInt(splitTime[0]);
            int minutes = Integer.parseInt(splitTime[1]);
            int seconds = Integer.parseInt(splitTime[2]);
            //set top yellow if seconds are even
            sb.append(BerlinClockHelper.getSecondsString(seconds));
            sb.append(NEW_LINE);
            //calculate hours
            sb.append(BerlinClockHelper.getHoursString(hours));
            sb.append(NEW_LINE);
            //calculate minutes
            sb.append(BerlinClockHelper.getMinutesString(minutes));
        }
        return sb.toString();
    }


}
