package com.messbook.messbook.Services;

import com.messbook.messbook.Daos.StudentDao;
import com.messbook.messbook.Entities.Student;
import com.messbook.messbook.Enums.StudentErrors;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public ResponseWithError<Student, StudentErrors> getStudentInformation(String student_roll_number) {
        ResponseWithError<Student, StudentErrors> response = new ResponseWithError<>();
        Student student = studentDao.getStudent(student_roll_number);
        response.setResponse(student);

        if(student == null) {
            response.configAsFailed("cannot fetch student information");
        }

        return response;
    }
}
