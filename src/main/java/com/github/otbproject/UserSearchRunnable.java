package com.github.otbproject;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import pro.beam.api.response.users.UserSearchResponse;
import pro.beam.api.services.impl.UsersService;

/**
 * Created by Justin on 20/03/2015.
 */
public class UserSearchRunnable implements Runnable {
    ListenableFuture<UserSearchResponse> userSearchFuture;
    public UserSearchRunnable (String user){
        userSearchFuture = App.beam.use(UsersService.class).search(user);
    }

    @Override
    public void run() {
        Futures.addCallback(userSearchFuture, new UserSearchResponseHandler());
    }
}
