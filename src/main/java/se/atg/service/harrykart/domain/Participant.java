package se.atg.service.harrykart.domain;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.math.BigInteger;

public class Participant {

    private BigInteger laneNumber;
    private String participantName;
    private double totalTime;

    public BigInteger getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(BigInteger laneNumber) {
        this.laneNumber = laneNumber;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

}
