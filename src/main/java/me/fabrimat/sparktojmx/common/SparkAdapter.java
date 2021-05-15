package me.fabrimat.sparktojmx.common;

import com.google.common.util.concurrent.AtomicDouble;
import me.lucko.spark.api.Spark;
import me.lucko.spark.api.SparkProvider;
import me.lucko.spark.api.statistic.StatisticWindow;
import me.lucko.spark.api.statistic.misc.DoubleAverageInfo;
import me.lucko.spark.api.statistic.types.DoubleStatistic;
import me.lucko.spark.api.statistic.types.GenericStatistic;

public class SparkAdapter {

    private static SparkAdapter instance;

    private Spark spark;
    private final TPS cacheTPS;
    private final MSPT cacheMSPT;

    private SparkAdapter() {
        cacheTPS = new TPS(0d, 0d, 0d, 0d, 0d);
        cacheMSPT = new MSPT(0d, 0d);

        updateTPS();
        updateMSPT();
    }

    public static SparkAdapter getInstance() {
        if(instance == null) {
            instance = new SparkAdapter();
        }
        return instance;
    }

    public synchronized void updateTPS() {
        if(spark == null) {
            try {
                spark = SparkProvider.get();
            } catch(IllegalStateException e) {
                return;
            }
        }

        DoubleStatistic<StatisticWindow.TicksPerSecond> tps = spark.tps();

        if(tps != null) {
            cacheTPS.setLast5Sec(tps.poll(StatisticWindow.TicksPerSecond.SECONDS_5));
            cacheTPS.setLast10Sec(tps.poll(StatisticWindow.TicksPerSecond.SECONDS_10));
            cacheTPS.setLast1Min(tps.poll(StatisticWindow.TicksPerSecond.MINUTES_1));
            cacheTPS.setLast5Min(tps.poll(StatisticWindow.TicksPerSecond.MINUTES_5));
            cacheTPS.setLast15Min(tps.poll(StatisticWindow.TicksPerSecond.MINUTES_15));
        }
    }

    public synchronized void updateMSPT() {
        if(spark == null) {
            try {
                spark = SparkProvider.get();
            } catch(IllegalStateException e) {
                return;
            }
        }

        GenericStatistic<DoubleAverageInfo, StatisticWindow.MillisPerTick> mspt = spark.mspt();

        if(mspt != null) {
            cacheMSPT.setLast10Sec(mspt.poll(StatisticWindow.MillisPerTick.SECONDS_10).percentile95th());
            cacheMSPT.setLast1Min(mspt.poll(StatisticWindow.MillisPerTick.MINUTES_1).percentile95th());
        }
    }

    public synchronized TPS getTPS() {
        return cacheTPS;
    }

    public synchronized MSPT getMSPT() {
        return cacheMSPT;
    }

    public static class TPS {
        private final AtomicDouble last5Sec;
        private final AtomicDouble last10Sec;
        private final AtomicDouble last1Min;
        private final AtomicDouble last5Min;
        private final AtomicDouble last15Min;

        public TPS(double last5Sec, double last10Sec, double last1Min, double last5Min, double last15Min) {
            this.last5Sec = new AtomicDouble(last5Sec);
            this.last10Sec = new AtomicDouble(last10Sec);
            this.last1Min = new AtomicDouble(last1Min);
            this.last5Min = new AtomicDouble(last5Min);
            this.last15Min = new AtomicDouble(last15Min);
        }

        public double getLast5Sec() {
            return last5Sec.doubleValue();
        }

        public void setLast5Sec(double last5Sec) {
            this.last5Sec.set(last5Sec);
        }

        public double getLast10Sec() {
            return last10Sec.doubleValue();
        }

        public void setLast10Sec(double last10Sec) {
            this.last10Sec.set(last10Sec);
        }

        public double getLast1Min() {
            return last1Min.doubleValue();
        }

        public void setLast1Min(double last1Min) {
            this.last1Min.set(last1Min);
        }

        public double getLast5Min() {
            return last5Min.doubleValue();
        }

        public void setLast5Min(double last5Min) {
            this.last5Min.set(last5Min);
        }

        public double getLast15Min() {
            return last15Min.doubleValue();
        }

        public void setLast15Min(double last15Min) {
            this.last15Min.set(last15Min);
        }
    }

    public static class MSPT {
        private final AtomicDouble last10Sec;
        private final AtomicDouble last1Min;

        public MSPT(double last10Sec, double last1Min) {
            this.last10Sec = new AtomicDouble(last10Sec);
            this.last1Min = new AtomicDouble(last1Min);
        }

        public double getLast10Sec() {
            return last10Sec.doubleValue();
        }

        public void setLast10Sec(double last10Sec) {
            this.last10Sec.set(last10Sec);
        }

        public double getLast1Min() {
            return last1Min.doubleValue();
        }

        public void setLast1Min(double last1Min) {
            this.last1Min.set(last1Min);
        }
    }
}
