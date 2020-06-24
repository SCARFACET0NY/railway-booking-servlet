package com.anton.railway.booking.command.view;

import com.anton.railway.booking.repository.dto.PaidTicket;
import com.anton.railway.booking.repository.entity.Trip;
import com.anton.railway.booking.service.TicketService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AdminCommandTest {
    private static final List<PaidTicket> tickets = new ArrayList<>();
    private static final Trip trip = new Trip();
    private static final Integer NUMBER_OF_PAGES = 2;
    private static final Integer PAGE_NUMBER = 0;

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    TicketService ticketService;
    AdminCommand adminCommand;

    @BeforeAll
    static void beforeAll() {
        trip.setTripId(1L);

        PaidTicket ticket = PaidTicket.builder().build();
        PaidTicket ticket1 = PaidTicket.builder().build();

        tickets.add(ticket);
        tickets.add(ticket1);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        adminCommand = new AdminCommand(ticketService);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void adminPageWithSetTripTest() throws Exception {
        when(session.getAttribute("selectedTrip")).thenReturn(trip);
        when(session.getAttribute("page")).thenReturn(PAGE_NUMBER);
        when(ticketService.findPaidTicketsPageByTripId(anyLong(), anyInt())).thenReturn(tickets);
        when(ticketService.getNumberOfPaidTicketsPagesByTripId(anyLong())).thenReturn(NUMBER_OF_PAGES);

        String adminPage = adminCommand.process(request, response);

        assertEquals("admin", adminPage);

        verify(session).setAttribute("paidTickets", tickets);
        verify(session).setAttribute("numOfPages", NUMBER_OF_PAGES);
        verify(session).setAttribute("page", PAGE_NUMBER);
    }

    @Test
    void adminPageWithoutTripTest() throws Exception {
        when(session.getAttribute("selectedTrip")).thenReturn(null);

        String adminPage = adminCommand.process(request, response);

        assertEquals("admin", adminPage);

        verify(session).removeAttribute("paidTickets");
    }
}