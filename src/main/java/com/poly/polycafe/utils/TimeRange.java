package com.poly.polycafe.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeRange {

    private LocalDateTime begin;
    private LocalDateTime end;

    public static TimeRange today() {
        LocalDate today = LocalDate.now();
        LocalDateTime begin = today.atStartOfDay();
        LocalDateTime end = begin.plusDays(1);
        return new TimeRange(begin, end);
    }

    public static TimeRange thisWeek() {
        LocalDate now = LocalDate.now();
        LocalDate beginDate = now.minusDays(now.getDayOfWeek().getValue() - 1);
        LocalDateTime begin = beginDate.atStartOfDay();
        LocalDateTime end = begin.plusDays(7);
        return new TimeRange(begin, end);
    }

    public static TimeRange thisMonth() {
        LocalDate now = LocalDate.now();
        LocalDate beginDate = now.withDayOfMonth(1);
        LocalDateTime begin = beginDate.atStartOfDay();
        LocalDateTime end = begin.plusMonths(1);
        return new TimeRange(begin, end);
    }

    public static TimeRange thisQuarter() {
        LocalDate now = LocalDate.now();
        int firstMonth = now.getMonth().firstMonthOfQuarter().getValue();
        LocalDate beginDate = now.withMonth(firstMonth).withDayOfMonth(1);
        LocalDateTime begin = beginDate.atStartOfDay();
        LocalDateTime end = begin.plusMonths(3);
        return new TimeRange(begin, end);
    }

    public static TimeRange thisYear() {
        LocalDate now = LocalDate.now();
        LocalDate beginDate = now.withMonth(1).withDayOfMonth(1);
        LocalDateTime begin = beginDate.atStartOfDay();
        LocalDateTime end = begin.plusYears(1);
        return new TimeRange(begin, end);
    }
}
