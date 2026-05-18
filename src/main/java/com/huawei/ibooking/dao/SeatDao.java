package com.huawei.ibooking.dao;

import com.huawei.ibooking.mapper.SeatMapper;
import com.huawei.ibooking.model.SeatDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SeatDao {
    @Autowired
    private SeatMapper seatMapper;

    public List<SeatDO> getSeats(final String date) {
        return seatMapper.getSeats(date);
    }

    public SeatDO getSeatById(final int id) {
        return seatMapper.getSeatById(id);
    }

    public boolean cancelReservation(final int id) {
        return seatMapper.cancelReservation(id, LocalDateTime.now()) > 0;
    }

    public boolean checkinSeat(final int id) {
        return seatMapper.checkinSeat(id, LocalDateTime.now()) > 0;
    }
}