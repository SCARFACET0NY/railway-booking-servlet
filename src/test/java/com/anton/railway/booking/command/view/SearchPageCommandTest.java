package com.anton.railway.booking.command.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchPageCommandTest {
    private static final String MAIL_SUCCESS = "mailSuccess";
    private static final String SUCCESS = "success";

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    SearchPageCommand searchPageCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        searchPageCommand = new SearchPageCommand();
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void searchPageTest() throws Exception {
        when(session.getAttribute(MAIL_SUCCESS)).thenReturn(SUCCESS);

        String searchPage = searchPageCommand.process(request, response);

        assertEquals("index", searchPage);

        verify(request).setAttribute(MAIL_SUCCESS, SUCCESS);
        verify(session).removeAttribute(MAIL_SUCCESS);
    }
}