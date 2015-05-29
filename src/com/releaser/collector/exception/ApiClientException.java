package com.releaser.collector.exception;

/**
 * Exception for api-clients
 */
public class ApiClientException extends Exception
{
    /**
     * Constructor
     *
     * @param message message
     * @param cause previous exception
     */
    public ApiClientException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Constructor
     *
     * @param message message
     */
    public ApiClientException(String message)
    {
        super(message);
    }
}
