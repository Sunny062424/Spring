package com.uploadcsv.demo2.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uploadcsv.demo2.helper.CsvHelper;
import com.uploadcsv.demo2.model.Members;
import com.uploadcsv.demo2.repository.MembersRepository;

@Service
public class CsvService {
    @Autowired
    MembersRepository repository;

    public void save(MultipartFile file) {
    try {
      List<Members> memberList = CsvHelper.csvToTutorials(file.getInputStream());
      repository.saveAll(memberList);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public List<Members> getAllMembers() {
    return repository.findAll();
  }
}
