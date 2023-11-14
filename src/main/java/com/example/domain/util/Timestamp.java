package com.example.domain.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timestamp {


    public static String timestamp() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return now.format(formatter);
    }
}
