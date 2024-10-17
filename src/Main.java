import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

enum LogLevel{ INFO, WARNING,ERROR
}
class Logger{
    private Logger(){}
    private static Logger _logger = null;
    private static LogLevel _logLevel = LogLevel.INFO;
    private static  void SetLogLevel(LogLevel level){
        _logLevel = level;
    }
    public static Logger GetInstanse() {
        if (_logger == null) {
            synchronized (Logger.class) {
                if (_logger == null) {
                    _logger = new Logger();
                }
            }
        }
        return _logger;
    }

    public void Log(String message, LogLevel level){
        if (level.ordinal() >= _logLevel.ordinal()) {
            Path filePath = Paths.get("C:/Users/VICTUS/Desktop/2 - 3 курс сабактар отчет/text.txt");

            String logMessage = level + " | " + message + System.lineSeparator();

            try {

                Files.write(filePath, logMessage.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.GetInstanse();
        logger.Log("This is an info message", LogLevel.INFO);
        logger.Log("This is a warning message", LogLevel.WARNING);
        logger.Log("This is an error message", LogLevel.ERROR);

        logger.Log("This info message should not be logged", LogLevel.INFO);
        logger.Log("This warning message will be logged", LogLevel.WARNING);
        logger.Log("This error message will be logged", LogLevel.ERROR);
    }
}

