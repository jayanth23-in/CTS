package DeepSkilling.Week1.Algorithms.DesignPatterns;

class Logger {
    private static Logger instance;
    private int logCount;

    private Logger() {
        logCount = 0;
    }
    //Ensuring that only one instance of the class exist
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logCount++;
        System.out.println("[LOG #" + logCount + "] " + message);
    }

    public int getLogCount() {
        return logCount;
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started");

        Logger logger2 = Logger.getInstance();
        logger2.log("User logged in");

        System.out.println("Does logger1 equal to logger2: " + (logger1 == logger2));
        System.out.println("Total logs recorded: " + logger1.getLogCount());
    }
}
