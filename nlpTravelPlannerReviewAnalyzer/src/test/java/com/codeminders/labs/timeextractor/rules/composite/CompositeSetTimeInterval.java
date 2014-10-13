package com.codeminders.labs.timeextractor.rules.composite;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.entities.TemporalExtraction;
import com.codeminders.labs.timeextractor.rules.general.GeneralTest;

public class CompositeSetTimeInterval extends GeneralTest {

    @Test
    public void compositeSetTimeInterval1() {

        String toPredict = "every Friday morning";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("every Friday morning", predicted.get(0).getTemporalExpression());
    }

    @Test
    public void compositeSetTimeInterval2() {
        String toPredict = "every Friday ";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("every Friday", predicted.get(0).getTemporalExpression());
    }

    @Test
    public void compositeSetTimeInterval3() {
        String toPredict = "11am-14pm of every month";
        List<TemporalExtraction> predicted = new ArrayList<TemporalExtraction>(service.extractDatesAndTimeFromText(toPredict, settings));
        assertEquals("11am-14pm of every month", predicted.get(0).getTemporalExpression());
    }
}
