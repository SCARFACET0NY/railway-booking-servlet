package com.anton.railway.booking.command.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class TripCommandTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    TripCommand tripCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        tripCommand = new TripCommand();
    }

    @Test
    void cartPageTest() throws Exception {
        String cartPage = tripCommand.process(request, response);

        assertEquals("trip", cartPage);
    }
}