package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.RouteDao;
import com.anton.railway.booking.repository.entity.Route;
import com.anton.railway.booking.service.RouteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RouteServiceImplTest {
    private static final long ID = 1L;
    private static final long ID2 = 2L;
    private static final Route route = new Route();
    private static final List<Route> routes = new ArrayList<>();
    @Mock
    RouteDao routeDao;
    RouteService routeService;

    @BeforeAll
    static void beforeAll() {
        Route route1 = new Route();
        route.setRouteId(ID);
        route1.setRouteId(ID2);

        routes.add(route);
        routes.add(route1);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        routeService = new RouteServiceImpl(routeDao);
    }

    @Test
    void findAllTest() {
        when(routeDao.findAll()).thenReturn(routes);
        List<Route> returnedRoutes = routeService.findAll();

        assertNotNull(returnedRoutes);
        assertEquals(2, returnedRoutes.size());
        assertEquals(routes, returnedRoutes);

        verify(routeDao).findAll();
    }

    @Test
    void findByIdTest() {
        when(routeDao.findById(anyLong())).thenReturn(Optional.of(route));
        Route returnedRoute = routeService.findById(ID);

        assertNotNull(returnedRoute);
        assertEquals(route, returnedRoute);
        assertEquals(route.getRouteId(), returnedRoute.getRouteId());

        verify(routeDao).findById(anyLong());
    }

    @Test
    void saveTest() {
        when(routeDao.save(any(Route.class))).thenReturn(ID);

        assertEquals(routeService.save(route), ID);

        verify(routeDao).save(any(Route.class));
    }

    @Test
    void deleteTest() {
        routeService.delete(route);

        verify(routeDao).delete(any(Route.class));
    }

    @Test
    void deleteByIdTest() {
        routeService.deleteById(ID);

        verify(routeDao).deleteById(anyLong());
    }
}