package com.sorfet.cstats;

/**
 * Created by Jope on 30.3.2017.
 */

public class KdRatio {

    static long time;
    String kdRatio;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getKdRatio() {
        return kdRatio;
    }

    public void setKdRatio(String kdRatio) {
        this.kdRatio = kdRatio;
    }

    public KdRatio(long time, String kdRatio) {
        this.time = time;
        this.kdRatio = kdRatio;
    }

    public KdRatio() {
    }
}
