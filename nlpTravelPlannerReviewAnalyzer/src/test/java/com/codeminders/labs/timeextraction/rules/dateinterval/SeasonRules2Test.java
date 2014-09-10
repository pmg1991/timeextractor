package com.codeminders.labs.timeextraction.rules.dateinterval;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class SeasonRules2Test extends GeneralTest {

    @Test
    public void SeasonRules2Test1() {

        String toPredict = "spring 2014";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("spring 2014", predicted.get(0).getTemporalExpression());
        assertEquals(3, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(5, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getYear());

    }

    @Test
    public void SeasonRules2Test2() {

        String toPredict = "winter 2014";
        List<TemporalExtraction> predicted = service.extractDatesAndTimeFromText(toPredict, null);
        assertEquals("winter 2014", predicted.get(0).getTemporalExpression());
        assertEquals(12, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getMonth());
        assertEquals(1, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getDay());
        assertEquals(2, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getMonth());
        assertEquals(28, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getDay());
        assertEquals(2014, predicted.get(0).getTemporal().get(0).getStartDate().getDate().getYear());
        assertEquals(2015, predicted.get(0).getTemporal().get(0).getEndDate().getDate().getYear());
    }
}
