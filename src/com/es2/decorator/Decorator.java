package com.es2.decorator;

import java.io.IOException;

public class Decorator implements AuthInterface {

    private AuthInterface auth;

    public Decorator(AuthInterface auth) {
        this.auth = auth;
    }

    public void auth(String username, String password) throws AuthException, IOException {
        if (!"admin".equals(username))
            throw new AuthException();
        if (!"admin".equals(password))
            throw new AuthException();
    }
}