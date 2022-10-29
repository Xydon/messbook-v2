package com.messbook.messbook.Services;

import com.messbook.messbook.Daos.SemesterDao;
import com.messbook.messbook.Entities.Semester_Details;
import com.messbook.messbook.Enums.SemesterErrors;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {

    @Autowired
    SemesterDao semesterDao;
    /*
    * TODO
    *  1. get latest semester
    * */

    public ResponseWithError<Semester_Details, SemesterErrors> getLatestSemester() {
        ResponseWithError<Semester_Details, SemesterErrors> response = new ResponseWithError<Semester_Details, SemesterErrors>();
        Semester_Details details = semesterDao.getLatestSemester();

        if(details == null) {
            response.setResponse(null);
            response.configError(SemesterErrors.FAILED, "failed to fetch the latest semester");
            return response;
        }

        response.configError(SemesterErrors.SUCCESS);
        response.setResponse(details);
        return response;
    }


}
