package com.xiangliban.managementsystem.pojo.Fix;
import lombok.Data;

@Data
public class FixDailyLine implements Comparable{
    private String fixDate;
    private int fixNumber;

    @Override
    public int compareTo(Object o) {
        FixDailyLine s = (FixDailyLine) o;
        if (this.fixDate.compareTo(s.fixDate) > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
