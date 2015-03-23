package com.github.otbproject;

import pro.beam.api.resource.BeamUser;
import pro.beam.api.response.users.UserSearchResponse;
import pro.beam.api.util.ResponseHandler;

/**
 * Created by Justin on 20/03/2015.
 */
public class UserSearchResponseHandler extends ResponseHandler<UserSearchResponse> {
    @Override
    public void onSuccess(UserSearchResponse beamUsers) {
        for (BeamUser user : beamUsers) {
            System.out.println(user.username);
            App.beamUser = user;
        }
    }
}
