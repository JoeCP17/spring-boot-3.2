package com.playground.springjdbc;

import com.playground.springjdbc.post.AnotherPost;
import com.playground.springjdbc.post.PostDataJdbcService;
import com.playground.springjdbc.post.PostJdbcService;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJdbcApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringJdbcApplication.class, args);
  }

//   find All을 통한 정상적으로 확인이 되는지 체크하기 위한 설정
  @Bean
  CommandLineRunner commandLineRunner(final PostJdbcService postJdbcService, final PostDataJdbcService postDataJdbcService) {
    return args -> {
      System.out.println(postJdbcService.findAll());
      System.out.println(postJdbcService.findAllByJdbcTemplate());
      System.out.println(postJdbcService.findAllByJdbcClient());

      postDataJdbcService.save(new AnotherPost(
          "1",
          "title",
          "slug",
          LocalDate.now(),
          0,
          "tags",
          0
      ));
      System.out.println(postDataJdbcService.findAll());

      AnotherPost slug = postDataJdbcService.findPostBySlug("slug");
      System.out.println(slug);
    };

  }

}
