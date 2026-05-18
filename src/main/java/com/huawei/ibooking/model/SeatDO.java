package com.huawei.ibooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class SeatDO {
    private int id;
    private String seatNumber;
    private int studyRoomId;
    private String studyRoom;
    private String buildingNumber;
    private String classRoomNumber;
    private String status;
    private String reservedBy;
    private LocalDateTime reservationTime;
    private LocalDateTime checkinTime;
    private LocalDateTime createdAt;
    private boolean hasPowerSocket;
    private boolean hasMobileTrackSocket;
    @JsonProperty("isNearWindow")
    private boolean nearWindow;
}