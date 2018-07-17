package se.atg.service.harrykart.domain;

import java.util.ArrayList;
import java.util.List;

public class ResponseJSON {

    List<Ranking> ranking = new ArrayList<>();

    public List<Ranking> getRanking() {
        return ranking;
    }

    public void setRanking(List<Ranking> ranking) {
        this.ranking = ranking;
    }
}
