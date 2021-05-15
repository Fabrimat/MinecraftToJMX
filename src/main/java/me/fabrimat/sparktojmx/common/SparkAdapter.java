package me.fabrimat.sparktojmx.common;

import me.lucko.spark.api.Spark;
import me.lucko.spark.api.SparkProvider;
import me.lucko.spark.api.statistic.StatisticWindow;
import me.lucko.spark.api.statistic.misc.DoubleAverageInfo;
import me.lucko.spark.api.statistic.types.DoubleStatistic;
import me.lucko.spark.api.statistic.types.GenericStatistic;

public class SparkAdapter {

    private static SparkAdapter instance;

    private Spark spark;
    private TPS cacheTPS;
    private MSPT cacheMSPT;

    private SparkAdapter() {
        updateTPS();
        updateMSPT();
    }

    public static SparkAdapter getInstance() {
        if(instance == null) {
            instance = new SparkAdapter();
        }
        return instance;
    }

    public void updateTPS() {
        if(spark == null) {
            try {
                spark = SparkProvider.get();
            } catch(IllegalStateException e) {
                cacheTPS = new TPS(0d, 0d, 0d, 0d, 0d);
                return;
            }
        }

        DoubleStatistic<StatisticWindow.TicksPerSecond> tps = spark.tps();

        if(tps != null) {
            double tpsLast5Secs = tps.poll(StatisticWindow.TicksPerSecond.SECONDS_5);
            double tpsLast10Secs = tps.poll(StatisticWindow.TicksPerSecond.SECONDS_10);
            double tpsLast1Min = tps.poll(StatisticWindow.TicksPerSecond.MINUTES_1);
            double tpsLast5Min = tps.poll(StatisticWindow.TicksPerSecond.MINUTES_5);
            double tpsLast15Min = tps.poll(StatisticWindow.TicksPerSecond.MINUTES_15);

            cacheTPS = new TPS(tpsLast5Secs, tpsLast10Secs, tpsLast1Min, tpsLast5Min, tpsLast15Min);
        }
    }

    public void updateMSPT() {
        if(spark == null) {
            try {
                spark = SparkProvider.get();
            } catch(IllegalStateException e) {
                cacheMSPT = new MSPT(0d, 0d);
                return;
            }
        }

        GenericStatistic<DoubleAverageInfo, StatisticWindow.MillisPerTick> mspt = spark.mspt();

        if(mspt != null) {
            DoubleAverageInfo msptLast10Secs = mspt.poll(StatisticWindow.MillisPerTick.SECONDS_10);
            DoubleAverageInfo msptLast1Min = mspt.poll(StatisticWindow.MillisPerTick.MINUTES_1);

            cacheMSPT = new MSPT(msptLast10Secs.percentile95th(), msptLast1Min.percentile95th());
        }
    }

    public TPS getTPS() {
        return cacheTPS;
    }

    public MSPT getMSPT() {
        return cacheMSPT;
    }

    public static class TPS {
        public final double last5Sec;
        public final double last10Sec;
        public final double last1Min;
        public final double last5Min;
        public final double last15Min;

        public TPS(double last5Sec, double last10Sec, double last1Min, double last5Min, double last15Min) {
            this.last5Sec = last5Sec;
            this.last10Sec = last10Sec;
            this.last1Min = last1Min;
            this.last5Min = last5Min;
            this.last15Min = last15Min;
        }
    }

    public static class MSPT {
        public final double last10Sec;
        public final double last1Min;

        public MSPT(double last10Sec, double last1Min) {
            this.last10Sec = last10Sec;
            this.last1Min = last1Min;
        }
    }
}
