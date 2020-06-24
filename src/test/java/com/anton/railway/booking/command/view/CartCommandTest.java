package com.anton.railway.booking.command.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class CartCommandTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    CartCommand cartCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cartCommand = new CartCommand();
    }

    @Test
    void cartPageTest() throws Exception {
        String cartPage = cartCommand.process(request, response);

        assertEquals("cart", cartPage);
    }
}