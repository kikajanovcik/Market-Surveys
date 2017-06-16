package io.kikajanovcik.maketsurveys.classes;

import java.util.Map;

public class Subscription {

    public enum FREQUENCY { DAILY, WEEKLY, MONTHLY };
    private FREQUENCY frequency;
    private Map<String, String> channels;

    public Subscription(FREQUENCY frequency, Map<String, String> channels) {
        this.frequency = frequency;
        this.channels = channels;
    }

    public Subscription() {}

    public FREQUENCY getFrequency() {
        return frequency;
    }

    public Map<String, String> getChannels() {
        return channels;
    }
}
