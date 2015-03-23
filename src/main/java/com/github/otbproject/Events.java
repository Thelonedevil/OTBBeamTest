package com.github.otbproject;

import pro.beam.api.resource.chat.events.EventHandler;
import pro.beam.api.resource.chat.events.IncomingMessageEvent;
import pro.beam.api.resource.chat.events.data.IncomingMessageData;

/**
 * Created by Justin on 19/03/2015.
 */
public class Events implements EventHandler<IncomingMessageEvent> {

    @Override
    public void onEvent(IncomingMessageEvent event) {
        System.out.println("EVENT HAPPENED PANIC");
        IncomingMessageData data = event.data;
        System.out.println(data.user_name + ":" + data.getMessage());
    }
}
