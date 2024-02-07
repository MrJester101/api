package com.dichotomy.google.auth.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import static java.time.temporal.ChronoUnit.*;

public class TimeUtils {
    public static String TIME_PATTERN = "yyyy:MM:dd:HH:mm";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static int SECONDS_IN_MS = 1000;
    public static int MINUTE_IN_MS = 60000;
    public static int HOUR_IN_MS = 3600000;
    public static int DAY_IN_MS = 86400000;

    public static DateTimeZone getDefaultTimeZone() {
        return DateTimeZone.UTC;
    }

    public static DateTime getCurrentDateTime(DateTimeZone timezone) {
        if (timezone == null) {
            timezone = getDefaultTimeZone();
        }

        return DateTime.now(timezone);
    }

    public static DateTime getDateTime(long time) {
        return new DateTime(time, getDefaultTimeZone());
    }

    public static DateTime getDateTime(long time, DateTimeZone timezone) {
        if (timezone == null) {
            return new DateTime(time);
        }

        return new DateTime(time, timezone);
    }

    public static long getRoundedOffTime(Long time, DateTimeZone timezone, TimeUnit unit, long base) {
        if (time == null) {
            time = getCurrentDateTime(null).getMillis();
        }

        if (unit == null) {
            unit = TimeUnit.MILLISECONDS;
        }

        long dividend = base;
        switch (unit) {
            case DAYS: {
                dividend = dividend * 24 * 60 * 60 * 1000;
                break;
            }
            case HOURS: {
                dividend = dividend * 60 * 60 * 1000;
                break;
            }
            case MINUTES: {
                dividend = dividend * 60 * 1000;
                break;
            }
            case SECONDS: {
                dividend = dividend * 1000;
                break;
            }
            default: {
                break;
            }
        }

        time = (time / dividend) * dividend;
        if (timezone != null) {
            time -= timezone.getOffset(time);
        }

        return time;
    }

    public static List<String> getDateRange(long startDate, long endDate) {
        LocalDate start = Instant.ofEpochMilli(startDate).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = Instant.ofEpochMilli(endDate).atZone(ZoneId.systemDefault()).toLocalDate();
        return start.datesUntil(end.plusDays(1))
                .map(LocalDate::toString)
                .collect(Collectors.toList());
    }

    public static String epochToDate(long timestamp, String format) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public static String epochToDate(long timestamp) {
        return epochToDate(timestamp, "yyyy-MM-dd");
    }

    public static long getRoundedOffTime(long time, TimeUnit unit, long base) {
        return getRoundedOffTime(time, null, unit, base);
    }

    public static long getRoundedOffTime(long time, TimeUnit unit) {
        return getRoundedOffTime(time, null, unit, 1);
    }

    public static long getCurrentTime(DateTimeZone timezone, TimeUnit unit, long base) {
        return getRoundedOffTime(getCurrentDateTime(timezone).getMillis(), null, unit, base);
    }

    public static long getCurrentTime(TimeUnit unit, int base) {
        return getRoundedOffTime(getCurrentDateTime(null).getMillis(), null, unit, base);
    }

    public static long getCurrentTime(TimeUnit unit) {
        return getRoundedOffTime(getCurrentDateTime(null).getMillis(), null, unit, 1);
    }

    public static long getCurrentTime() {
        return getRoundedOffTime(getCurrentDateTime(null).getMillis(), null, null, 1);
    }

    public static long addTime(long time, String timezone, TimeUnit unit, int value) {
        DateTime date = new DateTime(time, DateTimeZone.forID(timezone));
        if (unit == null) {
            return date.getMillis();
        }

        switch (unit) {
            case MINUTES: {
                date = date.plusMinutes(value);
                break;
            }
            case HOURS: {
                date = date.plusHours(value);
                break;
            }
            case DAYS: {
                date = date.plusDays(value);
                break;
            }
            case WEEKS: {
                date = date.plusWeeks(value);
                break;
            }
            case MONTHS: {
                date = date.plusMonths(value);
                break;
            }
            case YEARS: {
                date = date.plusYears(value);
                break;
            }
            default: {
                date = date.plusSeconds(value);
                break;
            }
        }

        return date.getMillis();
    }

    public static long addTime(long time, TimeUnit unit, int value) {
        return addTime(time, getDefaultTimeZone().getID(), unit, value);
    }

    public static long subtractTime(long time, TimeUnit unit, int value) {
        DateTime date = new DateTime(time, getDefaultTimeZone());
        if (unit == null) {
            return date.getMillis();
        }

        switch (unit) {
            case MINUTES: {
                date = date.minusMinutes(value);
                break;
            }
            case HOURS: {
                date = date.minusHours(value);
                break;
            }
            case DAYS: {
                date = date.minusDays(value);
                break;
            }
            case WEEKS: {
                date = date.minusWeeks(value);
                break;
            }
            case MONTHS: {
                date = date.minusMonths(value);
                break;
            }
            case YEARS: {
                date = date.minusYears(value);
                break;
            }
            default: {
                date = date.minusSeconds(value);
                break;
            }
        }

        return date.getMillis();
    }

    public static long parse(String pattern, String date) {
        DateTime someDate = new DateTime(Long.valueOf(date));
        return someDate.getMillis();
    }
}
