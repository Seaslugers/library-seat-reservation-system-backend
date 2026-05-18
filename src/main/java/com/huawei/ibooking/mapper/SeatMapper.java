package com.huawei.ibooking.mapper;

import com.huawei.ibooking.model.SeatDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SeatMapper {
    List<SeatDO> getSeats(@Param("date") String date);

    SeatDO getSeatById(@Param("id") int id);

    int cancelReservation(@Param("id") int id, @Param("updatedAt") LocalDateTime updatedAt);

    int checkinSeat(@Param("id") int id, @Param("updatedAt") LocalDateTime updatedAt);
}