package com.es2.decorator;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecoratorPatternTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll public static void restoreStreams() {
        System.setOut(System.out);
    }

    @BeforeEach void setUp() throws Exception {
    }

    @DisplayName("Test if authentication is performed correctly using admin_user/admin_password")
    @Test void testRightAuthWithoutDecorators() throws AuthException, IOException {
        AuthInterface auth = new Auth();
        auth.auth("admin_user", "admin_password");
    }

    @DisplayName("Test if authentication is exception is thrown for bad authentication") @Test
    void testWrongAuthWithoutDecorators() throws AuthException, IOException {
        AuthInterface auth = new Auth();
        assertThrows(AuthException.class, () -> {
            auth.auth("admin_user1", "admin_password1");
        });
    }

    @DisplayName("Test if Logging is decorating correctly the authentication") @Test
    void testLoggingDecorator() throws AuthException, IOException {
        AuthInterface auth = new Logging(new Auth());
        auth.auth("admin_user", "admin_password");
        assertTrue(outContent.toString().contains("auth()"));
    }

    @DisplayName("Test if CommonWordsValidator is decorating correctly the authentication") @Test
    void testCommonWordsValidatorDecorator() throws AuthException, IOException {
        AuthInterface auth = new CommonWordsValidator(new Auth());
        auth.auth("admin_user", "admin_password");
    }

    @DisplayName("Test if CommonWordsValidator exception is thrown if the password is common") @Test
    void testCommonWordsValidatorDecoratorCommonPassword() throws AuthException, IOException {
        AuthInterface auth = new CommonWordsValidator(new Auth());
        assertThrows(AuthException.class, () -> {
            auth.auth("admin_user", "bird");
        });
    }

    @DisplayName("Test if Logging and CommonWordsValidator is decorating correctly the authentication")
    @Test void testLoggingCommonWordsValidatorDecorator() throws AuthException, IOException {
        AuthInterface auth = new CommonWordsValidator(new Logging(new Auth()));

        auth.auth("admin_user", "admin_password");
    }
}

