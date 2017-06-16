package io.kikajanovcik.maketsurveys.classes;

public class Subscription {

    public enum FREQUENCY { DAILY, WEEKLY, MONTHLY };
    private FREQUENCY frequency;
    private String[] channels;

    public Subscription(FREQUENCY frequency, String[] channels) {
        this.frequency = frequency;
        this.channels = channels;
    }

    public Subscription() {}

    public FREQUENCY getFrequency() {
        return frequency;
    }

    public String[] getChannels() {
        return channels;
    }
}
