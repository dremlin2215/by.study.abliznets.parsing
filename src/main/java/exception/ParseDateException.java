package exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParseDateException extends RuntimeException {
    private static final Logger LOGGER = LogManager.getLogger(ParseDateException.class);
    private static final long serialVersionUID = 3456L;

    public ParseDateException(String message) {
        super(message);
        LOGGER.warn("ParseDateException \n");
    }

    public ParseDateException() {
        super();
        LOGGER.warn("ParseDateException \n");
    }

    public ParseDateException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.warn("ParseDateException \n");
    }

    public ParseDateException(Throwable cause) {
        super(cause);
        LOGGER.warn("ParseDateException \n");
    }

    protected ParseDateException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        LOGGER.warn("ParseDateException \n");
    }
}
