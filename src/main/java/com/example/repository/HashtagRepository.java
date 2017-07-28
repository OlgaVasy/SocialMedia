package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entities.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {

	public Hashtag findByLabel(String label);

}
