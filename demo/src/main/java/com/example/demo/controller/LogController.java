package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LogController {

    @GetMapping("/log")
    public String readLogFile(@RequestParam("filename") String filename, Model model) throws IOException {
        String filepath = "/Users/sunny/" + filename;
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            Stack<String> stack = new Stack<>();
            String line;
            int count = 0;
            while ((line = br.readLine()) != null){
                if (line.contains("checkME")) { 
                    count++;
                }
                if(line.contains("Started")){
                    stack.push(line);
                }
                if(line.contains("Shut Down")){
                    stack.pop();
                }
            }

            List<String> errorList = new ArrayList<>();
            while (!stack.isEmpty()){
                errorList.add(stack.pop());
            }
            
            model.addAttribute("count", count);
            model.addAttribute("errorList", errorList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "logresult";
    }
    
}
