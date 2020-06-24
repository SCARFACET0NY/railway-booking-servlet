package com.anton.railway.booking.command.view;

import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.service.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ScheduleCommandTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    TripService tripService;
    ScheduleCommand scheduleCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        scheduleCommand = new ScheduleCommand(tripService);
    }

    @Test
    void schedulePageTest() throws Exception {
        List<TripDto> trips = new ArrayList<>();
        TripDto trip = TripDto.builder().build();
        TripDto trip1 = TripDto.builder().build();

        trips.add(trip);
        trips.add(trip1);

        when(tripService.findAllScheduledTrips()).thenReturn(trips);

        String schedulePage = scheduleCommand.process(request, response);

        assertEquals("schedule", schedulePage);

        verify(request).setAttribute("trips", trips);
    }
}