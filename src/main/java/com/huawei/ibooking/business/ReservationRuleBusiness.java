package com.huawei.ibooking.business;

import com.huawei.ibooking.dao.ReservationRuleDao;
import com.huawei.ibooking.model.ReservationRuleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationRuleBusiness {
    private static final int DEFAULT_RULE_ID = 1;

    @Autowired
    private ReservationRuleDao reservationRuleDao;

    public ReservationRuleDO getRules() {
        ReservationRuleDO rules = reservationRuleDao.getRules();
        if (rules != null) {
            return rules;
        }

        return defaultRules();
    }

    public boolean saveRules(final ReservationRuleDO rules) {
        rules.setId(DEFAULT_RULE_ID);
        return reservationRuleDao.saveRules(rules);
    }

    private ReservationRuleDO defaultRules() {
        ReservationRuleDO rules = new ReservationRuleDO();
        rules.setId(DEFAULT_RULE_ID);
        rules.setMaxReservationHours(4);
        rules.setMinAdvanceMinutes(0);
        rules.setOpeningTime("07:00");
        rules.setClosingTime("22:00");
        rules.setPreReservationReminderMinutes(15);
        rules.setPostReservationReminderMinutes(10);
        rules.setAutoCancelMinutes(15);
        rules.setReminderEmail(true);
        rules.setReminderWeChat(true);
        rules.setReminderSMS(false);
        rules.setCheckinCodeWeb(true);
        rules.setCheckinQRCode(true);
        rules.setCheckinCodeRefreshMinutes(1440);
        rules.setViolationRecordEnabled(true);
        rules.setViolationPolicy("limit");
        rules.setViolationThreshold(3);
        return rules;
    }
}