package com.messbook.messbook.Controllers;

import com.messbook.messbook.Entities.Department;
import com.messbook.messbook.Entities.Extra_Item_Menu;
import com.messbook.messbook.Entities.Hostel;
import com.messbook.messbook.Enums.CollegeErrors;
import com.messbook.messbook.Services.CollegeService;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CollegeController {
    @Autowired
    CollegeService collegeService;

    /*
     * TODO
     *  1. function to get the extra items of the college -- done
     *  2. function to get the hostel details -- done
     *
     * */

    /// checked
    @GetMapping("api/college/extra_items_menu")
    public ResponseWithError<List<Extra_Item_Menu>, CollegeErrors> getExtraItemsMenu() {
        return collegeService.getExtraMenu();
    }

    /// checked
    @GetMapping("api/college/hostel/{name}")
    public ResponseWithError<Hostel, CollegeErrors> getHostelDetails(@PathVariable String name) {
        return collegeService.getHostelDetails(name);
    }

    /// checked
    @GetMapping("api/college/department/{name}")
    public ResponseWithError<Department, CollegeErrors> getDepartmentDetails(@PathVariable String name) {
        return collegeService.getDepartmentDetails(name);
    }

}
