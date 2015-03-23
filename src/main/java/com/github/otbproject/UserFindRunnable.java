package com.github.otbproject;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import pro.beam.api.resource.BeamUser;
import pro.beam.api.response.users.UserSearchResponse;
import pro.beam.api.services.impl.UsersService;
import pro.beam.api.util.ResponseHandler;

/**
 * Created by Justin on 20/03/2015.
 */
public class UserFindRunnable implements  Runnable {

    ListenableFuture<BeamUser> userFindFuture;

    public UserFindRunnable(int id){
        userFindFuture = App.beam.use(UsersService.class).findOne(id);
    }

    @Override
    public void run() {
        Futures.addCallback(userFindFuture, new UserFindResponseHandler());
    }
}
