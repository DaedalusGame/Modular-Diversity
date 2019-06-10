package modulardiversity.jei.ingredients;

import modulardiversity.jei.IFakeIngredient;

public class Daylight implements IFakeIngredient {
    long timeMin;
    long timeMax;
    long timeModulo;
    boolean timeLocal;

    public Daylight(long timeMin, long timeMax, long timeModulo, boolean local) {
        this.timeMin = timeMin;
        this.timeMax = timeMax;
        this.timeModulo = timeModulo;
        this.timeLocal = local;
    }

    public String getClockFrame(int time) {
        int num = (int) Math.floor((time - 6000) / 375);
        if (num < 0) {
            num += 64;
        }
        String numString = Integer.toString(num);
        if (num < 10) numString = "0" + numString;
        return numString;
    }

    public static String getFormattedLocalTime(long time) {
        boolean am = true;
        double cfg = time;
        cfg+=6000;
        if (cfg > 23999) cfg -= 24000;
        if (cfg >= 12000) {
            am = false;
            cfg -= 12000;
        }
        cfg = Math.floor(cfg / 10) / 100;
        if (cfg > 12) cfg -= 12;

        int cfgi = (int) Math.floor(cfg);
        int cfgj = (int) Math.floor((cfg - cfgi)*60);

        return Integer.toString(cfgi) + (cfgj < 10 ? ":0" : ":") + Integer.toString(cfgj) + (am ? " a.m." : " p.m.");
    }

    public static String getFormattedGlobalTime(long min, long max, long mod) {
        if(max == min)
            return "exactly "+min+" ticks after every "+mod+" ticks";
        else if(max >= Long.MAX_VALUE)
            return min+" ticks after every "+mod+" ticks";
        else if(min <= 0)
            return "up to "+max+" ticks after every "+mod+" ticks";
        else if(min < max)
            return "between "+min+" and "+max+" ticks after every "+mod+" ticks";
        else
            return "not between "+min+" and "+max+" ticks after every "+mod+" ticks";
    }

    @Override
    public String getDisplayName() {
        if(timeLocal)
            return "Time Required: Between " + getFormattedLocalTime(timeMin) + " and " + getFormattedLocalTime(timeMax);
        else
            return "Time Required: " + getFormattedGlobalTime(timeMin,timeMax,timeModulo);
    }

    @Override
    public String getUniqueID() {
        return "daylight";
    }
}

