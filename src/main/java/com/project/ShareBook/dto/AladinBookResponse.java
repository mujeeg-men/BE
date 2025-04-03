package com.project.ShareBook.dto;

import com.project.ShareBook.Entity.Book;
import jakarta.persistence.Lob;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

@Data

public class AladinBookResponse {
    private List<AladinBookItem> item;

    public List<Book> toBooks() {
        if (item == null) {
            return Collections.emptyList();
        }
        return item.stream().map(AladinBookItem::toBook).collect(Collectors.toList());
    }
}

