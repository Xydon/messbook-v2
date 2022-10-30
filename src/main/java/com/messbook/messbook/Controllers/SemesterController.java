package com.messbook.messbook.Controllers;

import com.messbook.messbook.Entities.Semester_Details;
import com.messbook.messbook.Enums.SemesterErrors;
import com.messbook.messbook.Services.SemesterService;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin
public class SemesterController {
    @Autowired
    SemesterService semesterService;

    /// checked
    @GetMapping("api/semester/latestSemester")
    public ResponseWithError<Semester_Details, SemesterErrors> getLatestSemester() {
        return semesterService.getLatestSemester();
    }

    /// checked
    @GetMapping("api/semester/monthList")
    public ResponseWithError<List<Date>, SemesterErrors> getMonthListOfMonthsInCurrentSemester(@RequestParam(value="isCurrent") boolean isCurrent) {
        if(isCurrent) {
            return semesterService.listOfMonthInCurrentSemesterNow();
        } else {
            return semesterService.getMonthListOfMonthsInCurrentSemester();
        }
    }
}
