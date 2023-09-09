package com.uploadcsv.demo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uploadcsv.demo2.model.Members;

public interface MembersRepository extends JpaRepository<Members, String> {
}
