package com.pillll.pillll.database.converter;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;

/**
 * Created by Pardoux Romain on 11/01/2019
 */

public class DateConverter {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
