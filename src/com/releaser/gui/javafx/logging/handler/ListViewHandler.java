package com.releaser.gui.javafx.logging.handler;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * ListView Handler
 *
 * Passes log messages to a javafx ListView component
 */
public class ListViewHandler extends Handler
{
    private ListView<String> listView;

    public ListViewHandler(ListView<String> listView)
    {
        this.listView = listView;
    }

    /**
     * Publish a <tt>LogRecord</tt>.
     * <p>
     * The logging request was made initially to a <tt>Logger</tt> object,
     * which initialized the <tt>LogRecord</tt> and forwarded it here.
     * <p>
     * The <tt>Handler</tt>  is responsible for formatting the message, when and
     * if necessary.  The formatting should include localization.
     *
     * @param record description of the log event. A null record is
     *               silently ignored and is not published
     */
    @Override
    public void publish(LogRecord record)
    {
        ObservableList<String> currentItems = listView.getItems();
        String logMsg = getFormatter().format(record);

        currentItems.add(logMsg);
    }

    /**
     * Flush any buffered output.
     */
    @Override
    public void flush()
    {

    }

    /**
     * Close the <tt>Handler</tt> and free all associated resources.
     * <p>
     * The close method will perform a <tt>flush</tt> and then close the
     * <tt>Handler</tt>.   After close has been called this <tt>Handler</tt>
     * should no longer be used.  Method calls may either be silently
     * ignored or may throw runtime exceptions.
     *
     * @throws SecurityException if a security manager exists and if
     *                           the caller does not have <tt>LoggingPermission("control")</tt>.
     */
    @Override
    public void close() throws SecurityException
    {

    }
}
