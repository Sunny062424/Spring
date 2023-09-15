package com.kopo.memo;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("create")
    public String createTable() {
        DB db = new DB();
        db.createTable();
        return "테이블이 생성되었습니다.";
    }

    @PostMapping("/api/memo")
    public HashMap<String, String> insertCon(@RequestParam(value="content", defaultValue="") String content) {
		DB db = new DB();
        System.out.println("CS:"+content);
		db.insertData(content);
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("message", "success");
		return result;
    }

    @GetMapping("/api/memo")
    public ArrayList<Memo> hiApi() {
		DB db = new DB();
		ArrayList<Memo> result = db.selectData();
		return result;
    }

    @PostMapping(value="/api/memo/{idx}")
    public void modify(@PathVariable String idx, @RequestParam(value="content", defaultValue="")String content) {
        DB db = new DB();
        System.out.println("CS:"+content);
        db.updateData(idx, content);
    }

    @GetMapping(value="/api/memo/{idx}")
    public  void delete(@PathVariable String idx) {
        DB db = new DB();
        db.deleteData(idx);
    }
    
}