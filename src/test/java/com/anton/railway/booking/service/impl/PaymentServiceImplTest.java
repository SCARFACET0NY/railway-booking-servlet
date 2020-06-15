package com.anton.railway.booking.service.impl;

import com.anton.railway.booking.repository.dao.PaymentDao;
import com.anton.railway.booking.repository.dto.TicketDto;
import com.anton.railway.booking.repository.dto.TripSeatDto;
import com.anton.railway.booking.repository.entity.Payment;
import com.anton.railway.booking.repository.entity.Ticket;
import com.anton.railway.booking.repository.entity.TripSeat;
import com.anton.railway.booking.service.PaymentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PaymentServiceImplTest {
    private static final long ID = 1L;
    private static final long ID2 = 2L;
    private static final BigDecimal PRICE1 = BigDecimal.valueOf(200.0);
    private static final BigDecimal PRICE2 = BigDecimal.valueOf(300.0);
    private static final BigDecimal TOTAL = BigDecimal.valueOf(500.0);
    private static final Payment payment = new Payment();
    private static final List<Payment> payments = new ArrayList<>();
    private static final Map<Long, TicketDto> cart = new HashMap<>();
    @Mock
    PaymentDao paymentDao;
    PaymentService paymentService;

    @BeforeAll
    static void beforeAll() {
        Payment payment1 = new Payment();
        payment.setPaymentId(ID);
        payment1.setPaymentId(ID2);

        payments.add(payment);
        payments.add(payment1);

        cart.put(ID, TicketDto.builder()
                .tripSeatDto(TripSeatDto.builder()
                        .tripSeat(new TripSeat()).build())
                .ticket(Ticket.builder()
                        .price(PRICE1).build()).build());

        cart.put(ID2, TicketDto.builder()
                .tripSeatDto(TripSeatDto.builder()
                        .tripSeat(new TripSeat()).build())
                .ticket(Ticket.builder()
                        .price(PRICE2).build()).build());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        paymentService = new PaymentServiceImpl(paymentDao);
    }

    @Test
    void findAllTest() {
        when(paymentDao.findAll()).thenReturn(payments);
        List<Payment> returnedPayments = paymentService.findAll();

        assertNotNull(returnedPayments);
        assertEquals(2, returnedPayments.size());
        assertEquals(payments, returnedPayments);

        verify(paymentDao).findAll();
    }

    @Test
    void findByIdTest() {
        when(paymentDao.findById(anyLong())).thenReturn(Optional.of(payment));
        Payment returnedPayment = paymentService.findById(ID);

        assertNotNull(returnedPayment);
        assertEquals(payment, returnedPayment);
        assertEquals(payment.getPaymentId(), returnedPayment.getPaymentId());

        verify(paymentDao).findById(anyLong());
    }

    @Test
    void saveTest() {
        when(paymentDao.save(any(Payment.class))).thenReturn(ID);

        assertEquals(paymentService.save(payment), ID);

        verify(paymentDao).save(any(Payment.class));
    }

    @Test
    void deleteTest() {
        paymentService.delete(payment);

        verify(paymentDao).delete(any(Payment.class));
    }

    @Test
    void deleteByIdTest() {
        paymentService.deleteById(ID);

        verify(paymentDao).deleteById(anyLong());
    }

    @Test
    void createPaymentTest() {
        Payment createdPayment = paymentService.createPayment(TOTAL, ID);

        assertNotNull(createdPayment);
        assertEquals(TOTAL, createdPayment.getTotal());
        assertEquals(ID, createdPayment.getUserId());
    }

    @Test
    void getCartTotalTest() {
        BigDecimal returnedTotal = paymentService.getCartTotal(cart);

        assertEquals(TOTAL, returnedTotal);
    }

    @Test
    void savePaymentWithTicketsTest() {
        paymentService.savePaymentWithTickets(TOTAL, ID, cart);

        verify(paymentDao).savePaymentWithTickets(any(Payment.class), anyList(), anyList());
    }
}