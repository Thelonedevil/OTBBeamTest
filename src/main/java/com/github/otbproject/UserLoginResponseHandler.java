package com.github.otbproject;

import pro.beam.api.resource.BeamUser;
import pro.beam.api.util.ResponseHandler;

/**
 * Created by Justin on 21/03/2015.
 */
public class UserLoginResponseHandler extends ResponseHandler<BeamUser> {
    @Override
    public void onSuccess(BeamUser beamUser) {
        App.beamUser=beamUser;
        App.authed = true;
    }
}
