package com.huawei.ibooking.business;

import com.huawei.ibooking.dao.SeatDao;
import com.huawei.ibooking.model.SeatDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatBusiness {
    @Autowired
    private SeatDao seatDao;

    public List<SeatDO> getSeats(final String date) {
        return seatDao.getSeats(date);
    }

    public boolean cancelReservation(final int id) {
        SeatDO seat = seatDao.getSeatById(id);
        if (seat == null || !"reserved".equals(seat.getStatus())) {
            return false;
        }

        return seatDao.cancelReservation(id);
    }

    public boolean checkinSeat(final int id) {
        SeatDO seat = seatDao.getSeatById(id);
        if (seat == null || (!"reserved".equals(seat.getStatus()) && !"occupied".equals(seat.getStatus()))) {
            return false;
        }

        return seatDao.checkinSeat(id);
    }
}