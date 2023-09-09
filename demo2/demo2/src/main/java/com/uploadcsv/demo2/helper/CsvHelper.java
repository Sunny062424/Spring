// package com.uploadcsv.demo2.helper;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.List;

// import org.apache.commons.csv.CSVFormat;
// import org.apache.commons.csv.CSVParser;
// import org.apache.commons.csv.CSVRecord;
// import org.springframework.web.multipart.MultipartFile;

// import com.uploadcsv.demo2.model.Members;

// public class CsvHelper {
//     public static String TYPE = "text/csv";
//     static String[] HEADERs = {"membername", "memberid", "regidate", "expdate"};

//   public static boolean hasCSVFormat(MultipartFile file) {

//     if (!TYPE.equals(file.getContentType())) {
//       return false;
//     }

//     return true;
//   }

// public static List<Members> csvToTutorials(InputStream is) {
//     try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//         CSVParser csvParser = new CSVParser(fileReader,
//             CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

//       List<Members> memberList = new ArrayList<Members>();

//       Iterable<CSVRecord> csvRecords = csvParser.getRecords();

//       for (CSVRecord csvRecord : csvRecords) {
//         Members members = new Members(
//             csvRecord.get("회원명"),
//             csvRecord.get("회원번호"),
//             csvRecord.get("회원등록일자"),
//             csvRecord.get("회원만료일자")
//             );

//         memberList.add(members);
//       }

//       return memberList;
//     } catch (IOException e) {
//       throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
//     }
//   }
// }
package com.uploadcsv.demo2.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.uploadcsv.demo2.model.Members;

public class CsvHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERS = {"회원명", "회원번호", "회원등록일자", "회원만료일자"}; // HEADERs를 HEADERS로 수정

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType()); // 간단한 논리는 한 줄로 표현 가능
    }

    public static List<Members> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withHeader(HEADERS).withIgnoreHeaderCase().withTrim())) { // 헤더를 명시적으로 지정

            List<Members> memberList = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) { // Iterable을 직접 사용
                Members members = new Members(
                        csvRecord.get("회원명"),
                        csvRecord.get("회원번호"),
                        csvRecord.get("회원등록일자"),
                        csvRecord.get("회원만료일자")
                );

                memberList.add(members);
            }

            return memberList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
