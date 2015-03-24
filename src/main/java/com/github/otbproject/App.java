package com.github.otbproject;

import pro.beam.api.BeamAPI;
import pro.beam.api.resource.BeamUser;
import pro.beam.api.resource.chat.BeamChat;
import pro.beam.api.resource.chat.BeamChatConnectable;
import pro.beam.api.resource.chat.events.IncomingMessageEvent;
import pro.beam.api.resource.chat.methods.AuthenticateMessage;
import pro.beam.api.resource.chat.methods.ChatSendMethod;
import pro.beam.api.services.impl.ChatService;
import pro.beam.api.services.impl.UsersService;

import java.util.concurrent.ExecutionException;

/**
 * Created by Justin on 19/03/2015.
 */
public class App {

    static BeamAPI beam = new BeamAPI();
    static BeamUser beamUser;
    static BeamChat beamChat;
    static BeamChatConnectable beamChatConnectable;

    public static void main(String[] args) {
        try {
            beamUser = beam.use(UsersService.class).login("The_Lone_Devil", "NOPASS"/*Not actual password duh*/).get();
            beamChat = beam.use(ChatService.class).findOne(beamUser.channel.id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.print(beamUser);
        beamChatConnectable = beamChat.makeConnectable(beam);
        boolean connected = false;
        try {
            connected = beamChatConnectable.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (connected) {
            beamChatConnectable.send(AuthenticateMessage.from(beamUser.channel, beamUser, beamChat.authkey));
            beamChatConnectable.send(ChatSendMethod.of("Test1"));
            beamChatConnectable.on(IncomingMessageEvent.class, new Events());

        }

    }
}
