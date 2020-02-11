package com.theladders.avital.cc;

import java.time.LocalDate;

public class DateRange {
    private final LocalDate from;
    private final LocalDate to;

    public DateRange(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public boolean isBetween(LocalDate date) {
        if (to == null) {
            return isBeforeOrEquals(date);
        }
        if (from == null) {
            return isAfterOrEquals(date);
        }
        return isBeforeOrEquals(date) && isAfterOrEquals(date);
    }

    private boolean isAfterOrEquals(LocalDate date) {
        return !to.isBefore(date);
    }

    private boolean isBeforeOrEquals(LocalDate date) {
        return !from.isAfter(date);
    }
}
