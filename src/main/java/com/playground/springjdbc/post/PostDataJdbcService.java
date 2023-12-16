package com.playground.springjdbc.post;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostDataJdbcService {

  private final PostRepository postRepository;

  public PostDataJdbcService(final PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Transactional
  public void save(final AnotherPost post) {
    postRepository.save(post);
  }

  @Transactional(readOnly = true)
  public List<AnotherPost> findAll() {
    return postRepository.findAll();
  }

  @Transactional(readOnly = true)
  public AnotherPost findPostBySlug(final String slug) {
    return postRepository.findBySlugIgnoreCase(slug).orElseThrow(
        () -> new IllegalArgumentException("해당하는 slug가 없습니다.")
    );
  }

}
