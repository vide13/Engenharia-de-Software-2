package com.es2.decorator;

public class Auth implements AuthInterface {

    public void auth(String username, String password) throws AuthException {
        if (!"admin_user".equals(username)) throw new AuthException();
        if (!"admin_password".equals(password)) throw new AuthException();
    }
}
