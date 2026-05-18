package com.huawei.ibooking.controller;

import com.huawei.ibooking.model.StudyRoomDO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudyRoomController {
    private final DataSource dataSource;

    public StudyRoomController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping(value = "/api/studyrooms")
    public ResponseEntity<List<StudyRoomDO>> listStudyRooms() {
        List<StudyRoomDO> studyRooms = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, buildingNum, classRoomNum FROM tbl_study_room ORDER BY id")) {
            while (rs.next()) {
                StudyRoomDO room = new StudyRoomDO();
                room.setId(rs.getInt("id"));
                room.setBuildingNumber(rs.getString("buildingNum"));
                room.setClassRoomNumber(rs.getString("classRoomNum"));
                studyRooms.add(room);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(studyRooms, HttpStatus.OK);
    }
}
