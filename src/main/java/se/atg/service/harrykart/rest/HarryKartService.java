package se.atg.service.harrykart.rest;

import org.springframework.stereotype.Service;
import se.atg.bean.HarryKartType;
import se.atg.bean.ParticipantType;
import se.atg.service.harrykart.domain.Participant;
import se.atg.service.harrykart.domain.Ranking;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class HarryKartService {

    private static final double LOOP_LENGTH = 1000;

    List<Participant> prepareParticipantsData(HarryKartType harryKart, List<Participant> participantTimeList) {
        List<ParticipantType> participants = harryKart.getStartList().getParticipant();
        participants.forEach(p -> {
            Participant participant = new Participant();
            participant.setParticipantName(p.getName());
            participant.setLaneNumber(p.getLane());
            participant.setTotalTime(calculateTotalTime(harryKart, p.getBaseSpeed().intValue(), p.getLane().intValue()));
            participantTimeList.add(participant);

        });
        return participantTimeList;
    }

    private double calculateTotalTime(HarryKartType harryKartType, int baseSpeed, int lane) {

        Map<Number, Number> laneSpeedMap = new HashMap<>();
        laneSpeedMap.put(1, baseSpeed);
        IntStream.rangeClosed(1, harryKartType.getPowerUps().getLoop().size())
                .forEach(i -> harryKartType.getPowerUps().getLoop().get(i - 1).getLane().forEach(laneType -> {
                    if (laneType.getNumber().intValue() == lane) {
                        laneSpeedMap.put(i + 1, laneSpeedMap.get(i).intValue() + laneType.getValue().intValue());
                    }
                }));
        return totalTime(laneSpeedMap);
    }

    private double totalTime(Map<Number, Number> laneSpeedMap) {
        double totalTime;
        List<Double> time = new ArrayList<>();
        laneSpeedMap.forEach((k, v) -> time.add(LOOP_LENGTH / v.doubleValue()));
        totalTime = time.stream().mapToDouble(Double::doubleValue).sum();
        return totalTime;
    }

    List<Ranking> getRankings(List<Participant> participantTimeList) {
        participantTimeList.sort(Comparator.comparingDouble(Participant::getTotalTime));

        List<Ranking> outputRankingList = new ArrayList<>();
        IntStream.range(0, 3)
                .forEach((i) -> {
                    Participant participant = participantTimeList.get(i);
                    outputRankingList.add(new Ranking(i + 1, participant.getParticipantName()));
                });
        return outputRankingList;
    }
}
