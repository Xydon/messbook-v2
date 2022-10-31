package com.messbook.messbook.Daos;

import com.messbook.messbook.Entities.Department;
import com.messbook.messbook.Entities.Extra_Item_Menu;
import com.messbook.messbook.Entities.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollegeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
    * TODO
    *  1. function to get the extra items of the college -- done
    *  2. function to get the hostel details -- done
    *
    * */

    public List<Extra_Item_Menu> getExtraItemMenu() {
        String query = "SELECT * FROM extra_item_menu;";
        List<Extra_Item_Menu> extraItemMenuList = null;

        try {
            extraItemMenuList = jdbcTemplate.query(query, new BeanPropertyRowMapper<Extra_Item_Menu>(Extra_Item_Menu.class));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return extraItemMenuList;
    }

    public Hostel getHostelDetails(String hostelName) {
        String query = "SELECT * FROM Hostel WHERE name = ?;";
        Hostel hostel = null;

        try {
            hostel = jdbcTemplate.query(query, new BeanPropertyRowMapper<Hostel>(Hostel.class), hostelName).get(0);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return hostel;
    }

    public Department getDepartmentDetails(String departmentName) {
        String query = "SELECT * FROM Department WHERE name = ?";
        Department department = null;

        try {
            department = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Department.class), departmentName).get(0);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return department;
    }
}
