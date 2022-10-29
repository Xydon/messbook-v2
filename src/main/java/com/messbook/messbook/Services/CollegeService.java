package com.messbook.messbook.Services;

import com.messbook.messbook.Daos.CollegeDao;
import com.messbook.messbook.Entities.Extra_Item_Menu;
import com.messbook.messbook.Entities.Hostel;
import com.messbook.messbook.Enums.CollegeErrors;
import com.messbook.messbook.Enums.Errors;
import com.messbook.messbook.UtilsClasses.ResponseWithError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {
    @Autowired
    CollegeDao collegeDao;

    /*
     * TODO
     *  1. function to get the extra items of the college -- done
     *  2. function to get the hostel details -- done
     *
     * */

    public ResponseWithError<List<Extra_Item_Menu>, CollegeErrors> getExtraMenu() {
        ResponseWithError<List<Extra_Item_Menu>, CollegeErrors> response = new ResponseWithError<List<Extra_Item_Menu>, CollegeErrors>();
        List<Extra_Item_Menu> extraItemMenuList = collegeDao.getExtraItemMenu();

        if(extraItemMenuList == null) {
            response.configAsFailed("cannot fetch extra items");
            return response;
        }

        response.config(extraItemMenuList, Errors.SUCCESS);
        return response;
    }

    public ResponseWithError<Hostel, CollegeErrors> getHostelDetails(String hostelName) {
        Hostel hostel = collegeDao.getHostelDetails(hostelName);
        ResponseWithError<Hostel, CollegeErrors> response = new ResponseWithError<Hostel, CollegeErrors>();

        response.setResponse(hostel);

        if(hostel == null) {
            response.configAsFailed("failed to get the hostel");
            return response;
        }

        return response;
    }
}
