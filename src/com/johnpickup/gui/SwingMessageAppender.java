package com.johnpickup.gui;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import javax.swing.*;

/**
 * Simple log4j appender that appends the message text to the Swing textArea
 */
@RequiredArgsConstructor
public class SwingMessageAppender extends AppenderSkeleton {
    private final JTextArea textArea;

    @Override
    protected void append(LoggingEvent event)
    {
        if(event.getLevel().isGreaterOrEqual(Level.INFO)){
            textArea.append(event.getMessage().toString() + "\n");
        }
    }

    public void close()
    {
    }

    public boolean requiresLayout()
    {
        return false;
    }
}
