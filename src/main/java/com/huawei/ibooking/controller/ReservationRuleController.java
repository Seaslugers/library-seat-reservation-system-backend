package com.huawei.ibooking.controller;

import com.huawei.ibooking.business.ReservationRuleBusiness;
import com.huawei.ibooking.model.ReservationRuleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080"})
public class ReservationRuleController {
    @Autowired
    private ReservationRuleBusiness reservationRuleBusiness;

    @GetMapping(value = "/admin/reservation-rules")
    public ResponseEntity<Map<String, ReservationRuleDO>> getRules() {
        Map<String, ReservationRuleDO> response = new HashMap<>();
        response.put("data", reservationRuleBusiness.getRules());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/admin/reservation-rules")
    public ResponseEntity<Map<String, Object>> saveRules(@RequestBody ReservationRuleDO rules) {
        boolean result = reservationRuleBusiness.saveRules(rules);

        Map<String, Object> response = new HashMap<>();
        response.put("success", result);
        return new ResponseEntity<>(response, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}