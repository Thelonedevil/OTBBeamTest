package com.github.otbproject;

import pro.beam.api.BeamAPI;
import pro.beam.api.resource.BeamUser;
import pro.beam.api.resource.channel.BeamChannel;
import pro.beam.api.resource.chat.AbstractChatMethod;
import pro.beam.api.resource.chat.BeamChat;
import pro.beam.api.resource.chat.BeamChatConnectable;
import pro.beam.api.resource.chat.events.IncomingMessageEvent;
import pro.beam.api.resource.chat.methods.ChatSendMethod;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Justin on 19/03/2015.
 */
public class App {

    static BeamAPI beam = new BeamAPI();
    static BeamUser beamUser;
    static BeamChannel beamChannel;
    static BeamChat beamChat;
    static BeamChatConnectable beamChatConnectable;
    public static boolean authed;

    public static void main(String[] args) {
        Thread userSearch = new Thread(new  UserSearchRunnable("The_Lone_Devil"));
        userSearch.start();
        try {
            userSearch.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (beamUser == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread userFind = new Thread( new UserFindRunnable(beamUser.id));
        userFind.start();
        try {
            userFind.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (beamUser.channel == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        beamChannel = beamUser.channel;
        System.out.println("UserName = " + beamUser.username);
        System.out.println("DisplayName = " + beamUser.display_name);
        System.out.println("Date Created at = " + beamUser.created_at);
        System.out.println("Facebook = " + beamUser.social_facebook);
        System.out.println("Twitter = " + beamUser.social_twitter);
        System.out.println("Youtube = " + beamUser.social_youtube);
        System.out.println("Channel Rating = " + beamChannel.audience);
        System.out.println("Channel Type = " + beamChannel.type);
        System.out.println("Channel CoStream Pref = " + beamChannel.allow_costream);
        System.out.println("Channel name = " + beamChannel.name);
        System.out.println("Channel body = " + beamChannel.body);

        Thread login = new Thread(new UserLoginRunnable(beamUser.username,"Nopass1."));
        login.start();
        try {
            login.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!authed){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Thread chatFind = new Thread( new ChatFindRunnable(beamChannel.id));
        chatFind.start();
        try {
            chatFind.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (beamChat == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(String point : beamChat.endpoints){
            System.out.println("Endpoint, " + point);
        }
        System.out.println("Chat Auth = "+beamChat.authkey);
        System.out.println("Chat Role = " + beamChat.role);
        beamChatConnectable = beamChat.makeConnectable(beam);
        beamChatConnectable.connect();
        beamChatConnectable.on(IncomingMessageEvent.class, new Events());

    }
}
