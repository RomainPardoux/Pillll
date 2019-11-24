package com.pillll.pillll.model.converter;

import androidx.room.TypeConverter;
import java.util.Date;

/**
 * Class used to persist Long instead of Date object because room doesn't know how to persist Date object.
 * 2 functions are defined, one that converts a Date object to a Long object and another that performs the inverse conversion.
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class DateConverter {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date();
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
