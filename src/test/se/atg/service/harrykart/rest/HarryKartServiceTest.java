package se.atg.service.harrykart.rest;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import se.atg.bean.HarryKartType;
import se.atg.bean.ParticipantType;
import se.atg.bean.StartListType;
import se.atg.service.harrykart.domain.Participant;
import se.atg.service.harrykart.domain.Ranking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class HarryKartServiceTest {
    private HarryKartService harryKartService = new HarryKartService();

    @Test
    public void prepareParticipantsData() {
        System.out.println("test");
        HarryKartType type = Mockito.mock(HarryKartType.class);
        StartListType startListType = Mockito.mock(StartListType.class);
        List<ParticipantType> participantTypeList = new ArrayList<>();
        when(startListType.getParticipant()).thenReturn(participantTypeList);
        when(type.getStartList()).thenReturn(startListType);
        List<Participant> participantList = new ArrayList<>();
        List<ParticipantType> participants = new ArrayList<>();
        System.out.println("type:::" + type);
        System.out.println("getStartList:::" + type.getStartList());
        when(type.getStartList().getParticipant()).thenReturn(participants);

        List<Participant> expected = harryKartService.prepareParticipantsData(type, participantList);
        assertThat(expected.size(), Is.is(0));

    }

    @Test
    public void prepareParticipantsData_onlyOneMock() {
        System.out.println("test");

        List<ParticipantType> participantTypeList = new ArrayList<>();

        StartListType startListType = Mockito.mock(StartListType.class);
        when(startListType.getParticipant()).thenReturn(participantTypeList);

        HarryKartType type = new HarryKartType();
        type.setStartList(startListType);

        System.out.println("type:::" + type);
        System.out.println("getStartList:::" + type.getStartList());

        List<Participant> expected = harryKartService.prepareParticipantsData(type, new ArrayList<>());
        assertThat(expected.size(), Is.is(0));
    }

    @Test
    public void getRankings() {
        Participant p1 = new Participant();
        p1.setLaneNumber(BigInteger.valueOf(1));
        p1.setParticipantName("horse1");
        p1.setTotalTime(198.789);
        Participant p2 = new Participant();
        p2.setLaneNumber(BigInteger.valueOf(2));
        p2.setParticipantName("horse2");
        p2.setTotalTime(172.45);
        Participant p3 = new Participant();
        p3.setLaneNumber(BigInteger.valueOf(3));
        p3.setParticipantName("horse3");
        p3.setTotalTime(180.450);
        List<Participant> participantTimeList = new ArrayList<>();
        participantTimeList.add(p1);
        participantTimeList.add(p2);
        participantTimeList.add(p3);

        List<Ranking> expected = harryKartService.getRankings(participantTimeList);
        assertThat(expected.size(), Is.is(3));

    }
}