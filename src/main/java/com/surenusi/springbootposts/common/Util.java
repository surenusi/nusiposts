package com.surenusi.springbootposts.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Util {
    //LocalDateTime타입 -> String타입 형변환
    public static String toStringDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(dateTimeFormatter::format)
                .orElse("");
    }
}
