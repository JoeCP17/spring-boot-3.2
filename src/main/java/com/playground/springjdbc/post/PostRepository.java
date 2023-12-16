package com.playground.springjdbc.post;

import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

public interface PostRepository extends ListCrudRepository<AnotherPost, String> {

  Optional<AnotherPost> findBySlugIgnoreCase(final String slug);
}
