package com.example.demo.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileDto;


@Controller
public class Testcon {
    

    @RequestMapping("/main")
    public String uploadpage(){
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("uploadfile") MultipartFile[] uploadfile, Model model)
                                                    throws IllegalStateException,
    IOException {
        List<FileDto> list = new ArrayList<>();
        for(MultipartFile file : uploadfile) {
            if (!file.isEmpty()){
                FileDto dto = new FileDto(file.getOriginalFilename(),file.getContentType());
                list.add(dto);

                File newFileName = new File(dto.getFileName());
                file.transferTo(newFileName); 
            }
        }
        model.addAttribute("files", list);
        return "result";
    }


}
