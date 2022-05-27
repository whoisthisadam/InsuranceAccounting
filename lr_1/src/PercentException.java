public class PercentException extends Exception{
    public PercentException() {
    }

    public PercentException(String message) {
        super(message);
    }

    public PercentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PercentException(Throwable cause) {
        super(cause);
    }

    public PercentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
