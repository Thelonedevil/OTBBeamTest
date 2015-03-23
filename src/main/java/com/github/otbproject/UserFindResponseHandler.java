package com.github.otbproject;

import pro.beam.api.resource.BeamUser;
import pro.beam.api.util.ResponseHandler;

/**
 * Created by Justin on 20/03/2015.
 */
public class UserFindResponseHandler extends ResponseHandler<BeamUser> {
    @Override
    public void onSuccess(BeamUser beamUser) {
        App.beamUser = beamUser;
    }
}
