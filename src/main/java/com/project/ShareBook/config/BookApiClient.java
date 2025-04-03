package com.project.ShareBook.config;

import com.project.ShareBook.Entity.Book;
import com.project.ShareBook.dto.AladinBookResponse;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookApiClient {
    @Value("${aladin.api.key}")
    private String ttbKey;

    private final RestTemplate restTemplate= new RestTemplate();;


    public List<Book> searchBooks(String bookName) {
        String apiUrl = "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx" +
            "?TTBKey=" + ttbKey +
            "&Query=" + bookName +
            "&QueryType=Title" +
            "&MaxResults=10" +
            "&SearchTarget=Book" +
            "&Output=js"+
            "&Version=20131101";
        log.info("Aladin API 호출 URL: {}", apiUrl);

//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", "application/json");
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<AladinBookResponse> response = restTemplate.getForEntity(apiUrl, AladinBookResponse.class);

        log.info("API 응답 코드: {}", response.getStatusCode());
        log.info("API 응답 본문: {}", response.getBody());

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().toBooks();
        }
        List<Book> books = response.getBody().toBooks();
        log.info("✅ [API 응답] '{}' 검색 결과 {}개", bookName, books.size());

        return books;
    }

}
