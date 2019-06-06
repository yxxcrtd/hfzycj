package com.zycj.sdk.common.mvc;

import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;

import org.springframework.util.StringUtils;

/**
 * Created by yi.wang1 on 2015/6/1.
 */
public class CustomTimeEditor extends PropertyEditorSupport {
    private final DateFormat dateFormat;
    private final boolean allowEmpty;
    private final int exactDateLength;

    public CustomTimeEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
    }

    public CustomTimeEditor(DateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
        this.dateFormat = dateFormat;
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
                setValue(new Time(this.dateFormat.parse(text).getTime()));
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }

    }

    public String getAsText() {
        Time value = (Time) this.getValue();
        return value != null ? this.dateFormat.format(value) : "";
    }
}
