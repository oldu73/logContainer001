import java.sql.Timestamp;
import java.util.Date;

public class LogOutputService {

    private OutputGenerator outputGenerator;

    public void setOutputGenerator(OutputGenerator outputGenerator) {
        this.outputGenerator = outputGenerator;
    }

    public void outputDebug(String message) {
        outputGenerator.output(now() + " [DEBUG] " + message);
    }

    public void outputInfo(String message) {
        outputGenerator.output(now() + " [INFO ] " + message);
    }

    // [WARN ]
    // [ERROR]
    // [FATAL]

    private String now() {
        // SRC: http://www.mkyong.com/java/how-to-get-current-timestamps-in-java/
        Date date= new java.util.Date();
        return (new Timestamp(date.getTime())).toString();
    }
}
