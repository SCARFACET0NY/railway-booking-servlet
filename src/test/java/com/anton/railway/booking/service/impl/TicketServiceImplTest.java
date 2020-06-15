package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.*;
import com.anton.railway.booking.repository.dto.PaidTicket;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.dto.TripDto;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.*;
import com.anton.railway.booking.repository.entity.enums.SeatStatus;
import com.anton.railway.booking.repository.entity.enums.WagonClass;
import com.anton.railway.booking.service.TicketService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TicketServiceImplTest {
    private static final long ID = 1L;
    private static final long ID2 = 2L;
    private static final BigDecimal PRICE_COEFFICIENT1 = BigDecimal.valueOf(1.5);
    private static final BigDecimal PRICE_COEFFICIENT2 = BigDecimal.valueOf(2.0);
    private static final BigDecimal PRICE = BigDecimal.valueOf(450.0);
    private static final int NUM_OF_SEATS = 15;
    private static final int NUM_OF_PAGES = 1;

    private static final Ticket ticket = new Ticket();
    private static final List<Ticket> tickets = new ArrayList<>();
    private static final Payment payment = new Payment();
    private static final User user = new User();
    private static final TripDto tripDto = new TripDto();

    private static final TripSeatDto tripSeatDto = new TripSeatDto();
    private static final Seat seat = new Seat();
    private static final TripSeat tripSeat = new TripSeat();
    private static final TripSeat tripSeat1 = new TripSeat();
    private static final List<TripSeat> tripSeats = new ArrayList<>();

    private static final WagonClass wagonClass = WagonClass.REGULAR;
    private static final Wagon wagon = new Wagon();
    private static final Wagon wagon1 = new Wagon();
    private static final String wagonNumber = "01";
    private static final WagonType wagonType = new WagonType();
    private static final WagonType wagonType1 = new WagonType();
    @Mock
    PaymentDao paymentDao;
    @Mock
    SeatDao seatDao;
    @Mock
    TripSeatDao tripSeatDao;
    @Mock
    TicketDao ticketDao;
    @Mock
    UserDao userDao;
    @Mock
    WagonDao wagonDao;
    @Mock
    WagonTypeDao wagonTypeDao;
    TicketService ticketService;

    @BeforeAll
    static void beforeAll() {
        Ticket ticket1 = Ticket.builder().ticketId(ID2).tripSeatId(ID2).paymentId(ID2).build();
        ticket.setTicketId(ID);
        ticket.setTripSeatId(ID);
        ticket.setPaymentId(ID);
        ticket.setPrice(PRICE);

        payment.setPaymentId(ID);
        payment.setUserId(ID);
        payment.setTotal(PRICE);
        user.setUserId(ID);
        tripDto.setMinPrice(BigDecimal.valueOf(300).setScale(2, RoundingMode.HALF_UP));

        tripSeat.setTripSeatId(ID);
        tripSeat1.setTripSeatId(ID2);
        tripSeat.setSeatId(ID);
        tripSeat1.setSeatId(ID2);
        seat.setWagonId(ID);

        wagon.setWagonTypeId(ID);
        wagon1.setWagonTypeId(ID2);
        wagonType.setPriceCoefficient(PRICE_COEFFICIENT1);
        wagonType1.setPriceCoefficient(PRICE_COEFFICIENT2);
        wagonType.setWagonClass(wagonClass);

        tripSeats.add(tripSeat);
        tripSeats.add(tripSeat1);
        tickets.add(ticket);
        tickets.add(ticket1);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ticketService = new TicketServiceImpl(paymentDao, seatDao, tripSeatDao, ticketDao, userDao, wagonDao, wagonTypeDao);
    }

    @Test
    void findAllTest() {
        when(ticketDao.findAll()).thenReturn(tickets);
        List<Ticket> returnedTickets = ticketService.findAll();

        assertNotNull(returnedTickets);
        assertEquals(2, returnedTickets.size());
        assertEquals(tickets, returnedTickets);

        verify(ticketDao).findAll();
    }

    @Test
    void findByIdTest() {
        when(ticketDao.findById(anyLong())).thenReturn(Optional.of(ticket));
        Ticket returnedTicket = ticketService.findById(ID);

        assertNotNull(returnedTicket);
        assertEquals(ticket, returnedTicket);
        assertEquals(ticket.getTicketId(), returnedTicket.getTicketId());

        verify(ticketDao).findById(anyLong());
    }

    @Test
    void saveTest() {
        when(ticketDao.save(any(Ticket.class))).thenReturn(ID);

        assertEquals(ticketService.save(ticket), ID);

        verify(ticketDao).save(any(Ticket.class));
    }

    @Test
    void deleteTest() {
        ticketService.delete(ticket);

        verify(ticketDao).delete(any(Ticket.class));
    }

    @Test
    void deleteByIdTest() {
        when(ticketDao.findById(anyLong())).thenReturn(Optional.of(ticket));
        when(tripSeatDao.findById(anyLong())).thenReturn(Optional.of(tripSeat));
        when(paymentDao.findById(anyLong())).thenReturn(Optional.of(payment));
        when(ticketDao.findTicketsByPaymentId(anyLong())).thenReturn(tickets);

        ticketService.deleteById(ID);
        assertEquals(BigDecimal.valueOf(0.0), payment.getTotal());

        verify(ticketDao).findById(anyLong());
        verify(tripSeatDao).findById(anyLong());
        verify(paymentDao).findById(anyLong());
        verify(ticketDao).findTicketsByPaymentId(anyLong());
        verify(tripSeatDao).save(any(TripSeat.class));
        verify(ticketDao).delete(any(Ticket.class));
        verify(paymentDao).save(any(Payment.class));
    }

    @Test
    void createTicketTest() {
        when(wagonTypeDao.findById(anyLong())).thenReturn(Optional.of(wagonType));
        Ticket createdTicket = ticketService.createTicket(tripDto, wagon, tripSeat);

        assertNotNull(ticket);
        assertEquals(tripSeat.getTripSeatId(), createdTicket.getTripSeatId());
        assertNotNull(createdTicket.getPrice());

        verify(wagonTypeDao).findById(anyLong());
    }

    @Test
    void createTicketDtoTest() {
        TicketDto createdTicketDto = ticketService.createTicketDto(ticket, tripDto, tripSeatDto, wagonNumber);

        assertNotNull(createdTicketDto);
        assertEquals(ticket, createdTicketDto.getTicket());
        assertEquals(tripDto, createdTicketDto.getTripDto());
        assertEquals(tripSeatDto, createdTicketDto.getTripSeatDto());
        assertEquals(wagonNumber, createdTicketDto.getWagonNumber());
    }

    @Test
    void changeTicketPriceTest() {
        when(paymentDao.findById(anyLong())).thenReturn(Optional.of(payment));
        when(paymentDao.save(any(Payment.class))).thenReturn(ID);
        when(wagonTypeDao.findById(eq(ID))).thenReturn(Optional.of(wagonType));
        when(wagonTypeDao.findById(eq(ID2))).thenReturn(Optional.of(wagonType1));

        ticketService.changeTicketPrice(ticket, wagon, wagon);
        assertEquals(PRICE, ticket.getPrice());

        ticketService.changeTicketPrice(ticket, wagon, wagon1);
        assertNotEquals(PRICE, ticket.getPrice());

        verify(paymentDao).findById(anyLong());
        verify(paymentDao).save(any(Payment.class));
        verify(wagonTypeDao, times(2)).findById(anyLong());

    }

    @Test
    void findPaidTicketsPageByTripIdTest() {
        when(tripSeatDao.findTripSeatsForTripByStatusPaged(anyLong(), eq(SeatStatus.OCCUPIED), anyInt(), anyInt()))
                .thenReturn(tripSeats);
        when(seatDao.findById(anyLong())).thenReturn(Optional.of(seat));
        when(wagonDao.findById(anyLong())).thenReturn(Optional.of(wagon));
        when(wagonTypeDao.findById(anyLong())).thenReturn(Optional.of(wagonType));
        when(ticketDao.findTicketByTripSeatId(anyLong())).thenReturn(ticket);
        when(paymentDao.findById(anyLong())).thenReturn(Optional.of(payment));
        when(userDao.findById(anyLong())).thenReturn(Optional.of(user));

        List<PaidTicket> paidTickets = ticketService.findPaidTicketsPageByTripId(ID, 1);

        assertNotNull(paidTickets);
        assertEquals(tripSeats.size(), paidTickets.size());

        verify(tripSeatDao).findTripSeatsForTripByStatusPaged(anyLong(), any(SeatStatus.class), anyInt(), anyInt());
        verify(seatDao, times(2)).findById(anyLong());
        verify(wagonDao, times(2)).findById(anyLong());
        verify(wagonTypeDao, times(2)).findById(anyLong());
        verify(ticketDao, times(2)).findTicketByTripSeatId(anyLong());
        verify(paymentDao, times(2)).findById(anyLong());
        verify(userDao, times(2)).findById(anyLong());
    }

    @Test
    void getNumberOfPaidTicketsPagesByTripIdTest() {
        when(tripSeatDao.getNumberOfTripSeatsForTripByStatus(anyLong(), eq(SeatStatus.OCCUPIED))).thenReturn(NUM_OF_SEATS);
        int numOfPages = ticketService.getNumberOfPaidTicketsPagesByTripId(ID);

        assertEquals(NUM_OF_PAGES, numOfPages);
    }
}