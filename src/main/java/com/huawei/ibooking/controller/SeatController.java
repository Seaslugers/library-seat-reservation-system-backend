package com.huawei.ibooking.controller;

import com.huawei.ibooking.business.SeatBusiness;
import com.huawei.ibooking.model.SeatDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080"})
public class SeatController {
    @Autowired
    private SeatBusiness seatBusiness;

    @GetMapping(value = "/seats")
    public ResponseEntity<List<SeatDO>> getSeats(@RequestParam(value = "date", required = false) String date) {
        return new ResponseEntity<>(seatBusiness.getSeats(date), HttpStatus.OK);
    }

    @PostMapping(value = "/reservations/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable("id") int id) {
        boolean result = seatBusiness.cancelReservation(id);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/seats/{id}/checkin")
    public ResponseEntity<Void> checkinSeat(@PathVariable("id") int id) {
        boolean result = seatBusiness.checkinSeat(id);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}