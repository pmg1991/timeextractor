import java.io.IOException;
import java.util.List;

import com.codeminders.labs.timeextractor.service.SUTimeService;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.time.SUTime;
import edu.stanford.nlp.time.SUTime.Temporal;
import edu.stanford.nlp.time.TimeExpression;
import edu.stanford.nlp.util.CoreMap;

public class TrainingMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        SUTimeService service = new SUTimeService();
        String date = "2014-07-30";
        String s = "3-320pm";
        String toPredict = "the final day";

        List<CoreMap> predicted = service.extractDatesAndTimeFromText(new String[] { toPredict },
                date);
        System.out.println(predicted);

        for (CoreMap cm : predicted) {
            cm.get(CoreAnnotations.TokensAnnotation.class);
            TimeExpression timeExpr = cm.get(TimeExpression.Annotation.class);
            Temporal temporal = timeExpr.getTemporal();

            System.out.println("\n **********SuTime************ ");
            System.out.println("TimeLabel:" + temporal.getTimeLabel());
            System.out.println("TimexValue:" + temporal.getTimexValue());
            System.out.println("Duration:" + temporal.getDuration());
            System.out.println("Period:" + temporal.getPeriod());
            System.out.println("Range:" + temporal.getRange());
            System.out.println("Time:" + temporal.getTime());
            System.out.println("Type:" + temporal.getTimexType());
            SUTime.Range range = temporal.getRange();
            // System.out.println(range.getJodaTimeInterval());

        }
    }
}