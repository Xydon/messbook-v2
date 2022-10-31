package com.messbook.messbook.Controllers;

import com.messbook.messbook.Entities.Student;
import com.messbook.messbook.Enums.StudentErrors;
import com.messbook.messbook.Services.StudentService;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    StudentService studentService;

    /// checked
    @GetMapping("api/student/information/{roll_number}")
    public ResponseWithError<Student, StudentErrors> getStudentInformation(@PathVariable String roll_number){
       return  studentService.getStudentInformation(roll_number);
    }

}
