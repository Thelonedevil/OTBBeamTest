package com.github.otbproject;

import pro.beam.api.resource.chat.BeamChat;
import pro.beam.api.util.ResponseHandler;

/**
 * Created by Justin on 20/03/2015.
 */
public class ChatFindResponseHandler extends ResponseHandler<BeamChat> {
    @Override
    public void onSuccess(BeamChat beamChat) {
        App.beamChat = beamChat;
    }
}
