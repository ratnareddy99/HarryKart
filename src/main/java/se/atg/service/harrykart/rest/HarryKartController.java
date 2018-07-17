package se.atg.service.harrykart.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.atg.bean.HarryKartType;
import se.atg.service.harrykart.domain.Participant;
import se.atg.service.harrykart.domain.ResponseJSON;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.*;

@RestController
@RequestMapping("/api")
public class HarryKartController {

    private static final Logger logger = LoggerFactory.getLogger(HarryKartController.class);
    @Autowired
    private
    HarryKartService service;

    @RequestMapping(method = RequestMethod.POST, path = "/play", consumes = "application/xml", produces = "application/json")
    public ResponseJSON playHarryKart(@RequestBody String inputXML) throws JAXBException {
        logger.info("playHarryKart method started");
        List<Participant> participantTimeList = new ArrayList<>();
        participantTimeList = service.prepareParticipantsData(processInputData(inputXML), participantTimeList);
        ResponseJSON response = new ResponseJSON();
        response.setRanking(service.getRankings(participantTimeList));
        return response;
    }

    private HarryKartType processInputData(String inputXML) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(HarryKartType.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(inputXML);
        return (HarryKartType) jaxbUnmarshaller.unmarshal(reader);
    }

}
