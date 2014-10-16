package com.codeminders.labs.timeextractor.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.TreeSet;

import org.joda.time.LocalDateTime;

import com.codeminders.labs.timeextractor.entities.Settings;
import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.utils.Utils;

/* Class to change timezone and date if null to current */

public class ProcessRulesService {

    public TreeSet<TemporalExtraction> changeRulesAccordingToUserTimeZoneAndCurrentDate(TreeSet<TemporalExtraction> receivedTemporals, Settings settings) {
        Iterator<TemporalExtraction> itr = receivedTemporals.iterator();
        int offsetTimeZone = settings.getTimezoneOffset();

        while (itr.hasNext()) {
            TemporalExtraction te = (TemporalExtraction) itr.next();
            List<Temporal> temporals = te.getTemporal();
            for (int i = 0; i < temporals.size(); i++) {
                Temporal temporal = temporals.get(i);
                TimeDate startTimeDate = temporal.getStartDate();
                TimeDate endTimeDate = temporal.getEndDate();
                startTimeDate = convertDateAndOffset(startTimeDate, offsetTimeZone);
                endTimeDate = convertDateAndOffset(endTimeDate, offsetTimeZone);
                startTimeDate = summerTime(startTimeDate);
                endTimeDate = summerTime(endTimeDate);
                temporal.setStartDate(startTimeDate);
                temporal.setEndDate(endTimeDate);
            }
        }
        return receivedTemporals;
    }

    private TimeDate summerTime(TimeDate timeDate) {
        if (timeDate == null) {
            return null;
        }

        if (summerTimeIsObserved(timeDate)) {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate);
            localDateTimeStart = localDateTimeStart.minusMinutes(60);
            timeDate = Utils.getTimeDate(localDateTimeStart, timeDate.getTime().getTimezoneOffset(), timeDate);
        }
        return timeDate;
    }

    private TimeDate convertDateAndOffset(TimeDate timeDate, int offsetTimeZone) {
        if (timeDate == null) {
            return null;
        }
        Time time = timeDate.getTime();
        if (timeDate != null && time != null && time.getTimezoneOffset() == -1000) {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate);
            localDateTimeStart = localDateTimeStart.plusMinutes(offsetTimeZone);
            timeDate = Utils.getTimeDate(localDateTimeStart, 0, timeDate);
        } else if (timeDate != null && time != null) {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate);
            localDateTimeStart = localDateTimeStart.plusMinutes(timeDate.getTime().getTimezoneOffset());
            timeDate = Utils.getTimeDate(localDateTimeStart, timeDate.getTime().getTimezoneOffset(), timeDate);
        } else {
            LocalDateTime localDateTimeStart = Utils.getTimeDate(timeDate);
            timeDate = Utils.getTimeDate(localDateTimeStart, offsetTimeZone, timeDate);
            localDateTimeStart = Utils.getTimeDate(timeDate);
            localDateTimeStart = localDateTimeStart.plusMinutes(offsetTimeZone);
            timeDate = Utils.getTimeDate(localDateTimeStart, timeDate.getTime().getTimezoneOffset(), timeDate);
        }

        return timeDate;

    }

    private boolean summerTimeIsObserved(TimeDate timeDate) {
        if (timeDate == null || timeDate.getTime() == null || timeDate.getTime().getTimezoneName() == null) {
            return false;
        }
        String timezone = timeDate.getTime().getTimezoneName();

        TimeZone tz = TimeZone.getTimeZone(timezone);
        Calendar c = Calendar.getInstance(tz);
        Date date = new Date();
        c.setTime(date);
        int offset = c.get(Calendar.DST_OFFSET);
        if (offset > 0) {
            return true;
        }
        return false;

    }

}
