package com.releaser.gui.javafx.logging.formatter;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * ListView Formatter
 *
 * Formats log messages for ListView output
 */
public class ListViewFormatter extends Formatter
{
    public final static String template = "[%s] %s";

    /**
     * Format the given log record and return the formatted string.
     * <p>
     * The resulting formatted String will normally include a
     * localized and formatted version of the LogRecord's message field.
     * It is recommended to use the {@link Formatter#formatMessage}
     * convenience method to localize and format the message field.
     *
     * @param record the log record to be formatted.
     * @return the formatted log record
     */
    @Override
    public String format(LogRecord record)
    {
        String message = String.format(
                template,
                record.getLevel().toString(),
                record.getMessage()
        );

        return message;
    }
}
