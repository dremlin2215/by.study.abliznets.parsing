package exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnimplementedOperationException extends RuntimeException {
    private static final Logger LOGGER = LogManager.getLogger(UnimplementedOperationException.class);
    private static final long serialVersionUID = -32356L;

    public UnimplementedOperationException(String message) {
        super(message);
        LOGGER.warn("UnimplementedOperationException \n");
    }

    public UnimplementedOperationException() {
        super();
        LOGGER.warn("UnimplementedOperationException \n");
    }

    public UnimplementedOperationException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.warn("UnimplementedOperationException \n");
    }

    public UnimplementedOperationException(Throwable cause) {
        super(cause);
        LOGGER.warn("UnimplementedOperationException \n");
    }

    protected UnimplementedOperationException(String message, Throwable cause,
                                              boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        LOGGER.warn("UnimplementedOperationException \n");
    }
}
