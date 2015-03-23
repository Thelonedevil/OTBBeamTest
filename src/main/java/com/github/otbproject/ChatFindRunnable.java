package com.github.otbproject;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import pro.beam.api.resource.chat.BeamChat;
import pro.beam.api.services.impl.ChatService;

/**
 * Created by Justin on 20/03/2015.
 */
public class ChatFindRunnable implements Runnable {

    ListenableFuture<BeamChat> beamChatListenableFuture;

    public ChatFindRunnable(int id){
        beamChatListenableFuture = App.beam.use(ChatService.class).findOne(id);
    }

    @Override
    public void run() {
        Futures.addCallback(beamChatListenableFuture, new ChatFindResponseHandler());
    }
}
