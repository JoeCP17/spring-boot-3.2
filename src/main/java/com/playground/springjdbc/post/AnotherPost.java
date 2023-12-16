package com.playground.springjdbc.post;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record AnotherPost(
    @Id
    String id,
    String title,
    String slug,
    LocalDate date,
    long timeToRead,
    String tags,
    @Version
    long version
) {

}
