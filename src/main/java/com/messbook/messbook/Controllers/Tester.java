package com.messbook.messbook.Controllers;

import com.messbook.messbook.UtilsClasses.ResponseWithError;
import com.messbook.messbook.UtilsClasses.ResponseWithErrorDebug;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;

class Dummy extends TimerTask {
    @Override
    public void run() {
        System.out.println("hello world");
    }
}

@RestController
public class Tester {
    Timer timer  = new Timer();

    @GetMapping("/xyz")
    public static ResponseWithErrorDebug<Boolean, Void> test() {
        return new ResponseWithErrorDebug<>();
    }
}
