package com.example.demo6.util;

import com.example.demo6.dto.*;
import com.example.demo6.entity.*;
import org.springframework.http.*;
import org.springframework.web.multipart.*;

import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Demo6Util {
//    try ~chatch는 예외처리 작업
    // throwssms 예외를 처리하지 않고 , 일시킨 사람에게 떠넘긴다

    public static String convertToBase64(MultipartFile file)throws IOException {
        byte[] fileBytes = file.getBytes() ;
        // contenetType는 파일의 형식.
        // base64 형식으로 데이터를 브라우저에 출력할 때
        //  데이터 앞에 파일을 형식을 지정하면 웹브라우저가 처리
        return "data:"+ file.getContentType() + ";base64,"+
                Base64.getEncoder().encodeToString(fileBytes);
    }

    public static PostDto.Pages getPages(int pageno, int pagesize, int blocksize, int totalcount ,List<Post> posts){

        int numberOfPages = (int)(Math.ceil((double)totalcount/pagesize));
        // pageno  prev ---> (pageno-1)/Block_Size
        //   1~5    0           ((1~4)-1)/5 =0
        //   6~10   5           ((6~10)-1)/5 =1
        int prev = ((pageno-1)/blocksize) * blocksize;
        int start = prev +1;
        int end = prev + blocksize;
        int next = end+1;

        //실제 페이지의 개수 13일 경우 end=13 이고 next=0
        if(end>=numberOfPages){
            end= numberOfPages;
            next=0;
        }
        return new PostDto.Pages(prev,start,end,next,pageno, posts);
    }

    private static final String PROFILE_FOLDER = System.getProperty("user.dir")+ File.separator +"upload"
            + File.separator + "profile" + File.separator;
    private static final String PROFILE_NAME ="3.jpg";
    public static String getDefaultBase64Profile() {
        try {
            //1. 폴더와 파일명으로 파일 객체를 생성
            File file = new File(PROFILE_FOLDER, PROFILE_NAME);
            //2. FileInputStream을 이용해 open한 파일을 byte로 읽어온다
            FileInputStream fis = new FileInputStream(file);
            byte[] fileBytes = fis.readAllBytes();
            //3. base64로 리턴
            return "data:"+ MediaType.IMAGE_JPEG_VALUE + ";base64,"+
                    Base64.getEncoder().encodeToString(fileBytes);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
