package com.messbook.messbook.Daos;

import com.messbook.messbook.Entities.Extra_Item_Menu;
import com.messbook.messbook.Entities.Semester_Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class SemesterDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
    * TODO
    *  1. getting the latest semester details
    *  2. getting the list of months in the current semester
    * */

    /// checked
    // getting the latest semester details
    public Semester_Details getLatestSemester() {
        String query = "SELECT * FROM Semester_Details ORDER BY start_date;";
        Semester_Details latestSemester = null;

        try {
            latestSemester = jdbcTemplate.query(query, new BeanPropertyRowMapper<Semester_Details>(Semester_Details.class)).get(0);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return latestSemester;
    }

    /// checked
    // list of months in the current semester
    public List<Date> getMonthListOfMonthsInCurrentSemester() {
        Semester_Details currentSemesterDetails = this.getLatestSemester();

         // getting the months
        int startDateMonth = currentSemesterDetails.getStart_date().getMonth();
        int endDateMonth = currentSemesterDetails.getEnd_date().getMonth();
        int startDateYear = currentSemesterDetails.getStart_date().getYear();
        int endDateYear = currentSemesterDetails.getEnd_date().getYear();

        List<Date> listOfMonths = new LinkedList<Date>();

        if(endDateMonth <= startDateMonth) {
            endDateMonth += 12;
        }

        for(int i = startDateMonth + 1; i <= endDateMonth; ++i) {
            int year = startDateYear;
            if(i > 12) {
                i-= 12;
                year = endDateYear;
            }
             Date firstDateOfMonth = new Date(year, i, 1);
            listOfMonths.add(firstDateOfMonth);
        }

        listOfMonths.add(currentSemesterDetails.getEnd_date());

        return listOfMonths;
    }

    /// checked
    // list of months in the current semester less than ending date
    public List<Date> listOfMonthInCurrentSemesterNow() {
        Semester_Details currentSemesterDetails = this.getLatestSemester();

        // getting the months
        int startDateMonth = currentSemesterDetails.getStart_date().getMonth();
        int endDateMonth = new java.util.Date().getMonth();
        int startDateYear = currentSemesterDetails.getStart_date().getYear();
        int endDateYear = new java.util.Date().getYear();

        if(endDateYear > currentSemesterDetails.getEnd_date().getYear() || endDateMonth > currentSemesterDetails.getEnd_date().getMonth()) return new LinkedList<>();

        List<Date> listOfMonths = new LinkedList<Date>();

        System.out.println(startDateMonth);
        System.out.println(endDateMonth);

        if(endDateMonth <= startDateMonth) {
            endDateMonth += 12;
        }

        for(int i = startDateMonth + 1; i <= endDateMonth; ++i) {
//            System.out.println(i);
            int year = startDateYear;
            if(i > 12) {
                i-= 12;
                year = endDateYear;
            }
            Date firstDateOfMonth = new Date(year, i, 1);
            listOfMonths.add(firstDateOfMonth);
        }

        return listOfMonths;
    }
}
