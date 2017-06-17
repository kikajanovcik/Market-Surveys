package io.kikajanovcik.maketsurveys.classes;

import java.util.List;

public class Subscription {

    public enum Frequency { DAILY, WEEKLY, MONTHLY };
    private Frequency frequency;
    private List<String> channels;

    public Subscription(Frequency frequency, List<String> channels) {
        this.frequency = frequency;
        this.channels = channels;
    }

    public Subscription() {}

    public Frequency getFrequency() {
        return frequency;
    }

    public List<String> getChannels() {
        return channels;
    }
}
