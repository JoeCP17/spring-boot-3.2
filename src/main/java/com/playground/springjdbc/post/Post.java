package com.playground.springjdbc.post;

import java.time.LocalDate;

public record Post(
    String id,
    String title,
    String slug,
    LocalDate date,
    long timeToRead,
    String tags
) {

}
