package com.github.otbproject;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import pro.beam.api.resource.BeamUser;
import pro.beam.api.services.impl.UsersService;

/**
 * Created by Justin on 21/03/2015.
 */
public class UserLoginRunnable implements Runnable {
    ListenableFuture<BeamUser> userLoginFuture;

    public UserLoginRunnable(String user, String pass){
        userLoginFuture = App.beam.use(UsersService.class).login(user,pass);
    }

    @Override
    public void run() {
        Futures.addCallback(userLoginFuture, new UserLoginResponseHandler());
    }
}
