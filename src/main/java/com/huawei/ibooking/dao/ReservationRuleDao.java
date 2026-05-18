package com.huawei.ibooking.dao;

import com.huawei.ibooking.mapper.ReservationRuleMapper;
import com.huawei.ibooking.model.ReservationRuleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationRuleDao {
    @Autowired
    private ReservationRuleMapper reservationRuleMapper;

    public ReservationRuleDO getRules() {
        return reservationRuleMapper.getRules();
    }

    public boolean saveRules(final ReservationRuleDO rules) {
        return reservationRuleMapper.saveRules(rules) > 0;
    }
}