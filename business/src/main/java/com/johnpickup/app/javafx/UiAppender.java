package com.johnpickup.app.javafx;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class UiAppender extends AppenderBase<ILoggingEvent> {
    private final Consumer<String> messageConsumer;

    @Override
    protected void append(ILoggingEvent event) {
        messageConsumer.accept(event.getFormattedMessage());
    }
}
