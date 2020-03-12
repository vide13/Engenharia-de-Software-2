package com.es2.decorator;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecoratorPatternTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll public static void restoreStreams() {
        System.setOut(System.out);
    }

    @DisplayName("Test if authentication is performed correctly using admin_user/admin_password")
    @Test public void testRightAuthWithoutDecorators() throws AuthException, IOException {
        AuthInterface auth = new Auth();
        auth.auth("admin_user", "admin_password");
    }

    @DisplayName("Test if authentication is exception is thrown for bad authentication") @Test
    public void testWrongAuthWithoutDecorators() {
        AuthInterface auth = new Auth();
        assertThrows(AuthException.class, () -> auth.auth("admin_user1", "admin_password1"));
    }

    @DisplayName("Test if Logging is decorating correctly the authentication") @Test
    public void testLoggingDecorator() throws AuthException, IOException {
        AuthInterface auth = new Logging(new Auth());
        auth.auth("admin_user", "admin_password");
        assertTrue(outContent.toString().contains("auth()"));
    }

    @DisplayName("Test if CommonWordsValidator is decorating correctly the authentication") @Test
    public void testCommonWordsValidatorDecorator() throws AuthException, IOException {
        AuthInterface auth = new CommonWordsValidator(new Auth());
        auth.auth("admin_user", "admin_password");
    }

    @DisplayName("Test if CommonWordsValidator exception is thrown if the password is common") @Test
    public void testCommonWordsValidatorDecoratorCommonPassword()
        throws AuthException, IOException {
        AuthInterface auth = new CommonWordsValidator(new Auth());
        assertThrows(AuthException.class, () -> {
            auth.auth("admin_user", "bird");
        });
    }

    @DisplayName("Test if Logging and CommonWordsValidator is decorating correctly the authentication")
    @Test public void testLoggingCommonWordsValidatorDecorator() throws AuthException, IOException {
        AuthInterface auth = new CommonWordsValidator(new Logging(new Auth()));

        auth.auth("admin_user", "admin_password");
    }
}

