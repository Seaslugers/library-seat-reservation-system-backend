package com.huawei.ibooking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReservationRuleDO {
    private int id;
    private int maxReservationHours;
    private int minAdvanceMinutes;
    private String openingTime;
    private String closingTime;
    private int preReservationReminderMinutes;
    private int postReservationReminderMinutes;
    private int autoCancelMinutes;
    private boolean reminderEmail;
    private boolean reminderWeChat;
    private boolean reminderSMS;
    private boolean checkinCodeWeb;
    private boolean checkinQRCode;
    private int checkinCodeRefreshMinutes;
    private boolean violationRecordEnabled;
    private String violationPolicy;
    private int violationThreshold;
}