package com.huawei.ibooking.controller;

import com.huawei.ibooking.business.StudyRoomBusiness;
import com.huawei.ibooking.model.StudyRoomDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080"})
public class StudyRoomApiController {
    @Autowired
    private StudyRoomBusiness studyRoomBusiness;

    @GetMapping(value = "/studyrooms")
    public ResponseEntity<List<StudyRoomDO>> getStudyRooms() {
        return new ResponseEntity<>(studyRoomBusiness.getStudyRooms(), HttpStatus.OK);
    }
}