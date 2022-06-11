package com.example.demo.domain.common;

import java.util.List;

public interface ReservationService {
    public List<Reservation> queryAll();
    public List<Reservation> query(String courtName);
}
