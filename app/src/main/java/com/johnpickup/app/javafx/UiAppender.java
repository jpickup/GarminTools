package com.johnpickup.app.javafx;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.function.Consumer;

public class UiAppender extends AppenderBase<ILoggingEvent> {
    private final Consumer<String> messageConsumer;

    public UiAppender(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    @Override
    protected void append(ILoggingEvent event) {
        messageConsumer.accept(event.getFormattedMessage());
    }
}
