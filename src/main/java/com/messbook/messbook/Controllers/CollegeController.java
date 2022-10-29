package com.messbook.messbook.Controllers;

import com.messbook.messbook.Entities.Extra_Item_Menu;
import com.messbook.messbook.Entities.Hostel;
import com.messbook.messbook.Enums.CollegeErrors;
import com.messbook.messbook.Services.CollegeService;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("api/college/extra_items_menu")
    public ResponseWithError<List<Extra_Item_Menu>, CollegeErrors> getExtraItemsMenu() {
        return collegeService.getExtraMenu();
    }

    @GetMapping("api/college/hostel")
    public ResponseWithError<Hostel, CollegeErrors> getHostelDetails(@RequestParam(value = "hostelName") String hostelName) {
        return collegeService.getHostelDetails(hostelName);
    }
}
