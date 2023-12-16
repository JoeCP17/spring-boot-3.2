package com.playground.springjdbc.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostJdbcService {

  private final DataSource dataSource;
  private final JdbcTemplate jdbcTemplate;
  private final JdbcClient jdbcClient;

  public PostJdbcService(
      final DataSource dataSource,
      final JdbcTemplate jdbcTemplate,
      final JdbcClient jdbcClient
      ) {
    this.dataSource = dataSource;
    this.jdbcTemplate = jdbcTemplate;
    this.jdbcClient = jdbcClient;
  }

  /**
   * @description : java + jdbc를 통한 findAll
   */
  @Transactional(readOnly = true)
  public List<Post> findAll() throws SQLException {
    Connection connection = dataSource.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM post");
    ResultSet resultSet = preparedStatement.executeQuery();

    List<Post> posts = new ArrayList<>();

    while (resultSet.next()) {
      posts.add(
          new Post(
              resultSet.getString("id"),
              resultSet.getString("title"),
              resultSet.getString("slug"),
              resultSet.getDate("date").toLocalDate(),
              resultSet.getLong("time_to_read"),
              resultSet.getString("tags")
          )
      );
    }
    return posts;
  }

  @Transactional(readOnly = true)
  public List<Post> findAllByJdbcTemplate() {
    return jdbcTemplate.query(
        "SELECT * FROM post",
        (resultSet, rowNum) -> new Post(
            resultSet.getString("id"),
            resultSet.getString("title"),
            resultSet.getString("slug"),
            resultSet.getDate("date").toLocalDate(),
            resultSet.getLong("time_to_read"),
            resultSet.getString("tags")
        )
    );
  }

  /**
   * @description : JdbcClient를 통한 findAll 내부 MappedClass를 통해 Post.class를 찾아서 매핑
   */
  @Transactional(readOnly = true)
  public List<Post> findAllByJdbcClient() {
    return jdbcClient.sql("SELECT * FROM post")
        .query(Post.class)
        .list();
  }

}
