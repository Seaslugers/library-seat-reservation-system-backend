package com.huawei.ibooking.mapper;

import com.huawei.ibooking.model.StudyRoomDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyRoomMapper {
    List<StudyRoomDO> getStudyRooms();
}