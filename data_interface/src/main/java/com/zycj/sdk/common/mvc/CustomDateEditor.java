package com.zycj.sdk.common.mvc;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * Created by yi.wang1 on 2015/6/1.
 */
public class CustomDateEditor extends PropertyEditorSupport {
    private final DateFormat defaultDateFormat;
    private final boolean allowEmpty;
    private final int exactDateLength;

    public CustomDateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.defaultDateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
    }

    public CustomDateEditor(DateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
        this.defaultDateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            this.setValue(null);
        } else {
            if ((text != null) && (this.exactDateLength >= 0) && (text.length() != this.exactDateLength)) {
                throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
            }
            try {
                DateFormat dateFormat = getSimpleDateFormatByText(text);
                setValue(new Date(dateFormat.parse(text).getTime()));
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }

    }

    private DateFormat getSimpleDateFormatByText(String text) {
        text = text.trim();
        Pattern timestampPattern = Pattern.compile("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");
        Matcher timestampMatcher = timestampPattern.matcher(text);
        Pattern datePattern = Pattern.compile("^\\d{4}-\\d{1,2}-\\d{1,2}$");
        Matcher dateMatcher = datePattern.matcher(text);
        Pattern timePattern = Pattern.compile("^\\d{1,2}:\\d{1,2}:\\d{1,2}");
        Matcher timeMatcher = timePattern.matcher(text);
        if (dateMatcher.matches()) {
            return new SimpleDateFormat("yyyy-MM-dd");
        } else if (timestampMatcher.matches()) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (timeMatcher.matches()) {
            return new SimpleDateFormat("HH:mm:ss");
        }
        return this.defaultDateFormat;
    }

    private DateFormat getSimpleDateFormatByValue(Date value) {
        if (value == null) {
            return this.defaultDateFormat;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(value);
            TimeZone timeZone = calendar.getTimeZone();
            long offsetTime = timeZone.getOffset(value.getTime());
            if (value.getTime() + offsetTime < 86400000 && value.getTime() + offsetTime >= 0) {
                return new SimpleDateFormat("HH:mm:ss");
            } else if ((value.getTime() + offsetTime) % (60 * 60 * 24 * 1000) == 0) {
                return new SimpleDateFormat("yyyy-MM-dd");
            } else {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        }
    }

    public String getAsText() {
        Date value = (Date) this.getValue();
        DateFormat dateFormat = getSimpleDateFormatByValue(value);
        return value != null ? dateFormat.format(value) : "";
    }
}
