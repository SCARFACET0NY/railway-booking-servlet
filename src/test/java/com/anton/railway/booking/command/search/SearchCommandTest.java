package com.anton.railway.booking.command.search;

import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.service.TripService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchCommandTest {
    private static final String DEPARTURE_CITY = "Kiev";
    private static final String ARRIVAL_CITY = "Lviv";
    private static final String DATE = "2020-07-01";
    private static final List<TripDto> trips = new ArrayList<>();

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    TripService tripService;
    SearchCommand searchCommand;

    @BeforeAll
    static void beforeAll() {
        TripDto trip = TripDto.builder().build();
        TripDto trip1 = TripDto.builder().build();

        trips.add(trip);
        trips.add(trip1);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        searchCommand = new SearchCommand(tripService);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("departure")).thenReturn(DEPARTURE_CITY);
        when(request.getParameter("arrival")).thenReturn(ARRIVAL_CITY);
    }

    @Test
    void searchTest() throws Exception {
        when(tripService.searchTrips(anyString(), anyString())).thenReturn(trips);
        when(request.getParameter("date")).thenReturn("");

        String searchPage = searchCommand.process(request, response);

        assertEquals("index", searchPage);

        verify(request).setAttribute("trips", trips);
        verify(tripService).searchTrips(anyString(), anyString());
    }

    @Test
    void searchWithDateTest() throws Exception {
        when(tripService.searchTrips(anyString(), anyString(), any(LocalDate.class))).thenReturn(trips);
        when(request.getParameter("date")).thenReturn(DATE);

        String searchPage = searchCommand.process(request, response);

        assertEquals("index", searchPage);

        verify(request).setAttribute("trips", trips);
        verify(tripService).searchTrips(anyString(), anyString(), any(LocalDate.class));
    }
}