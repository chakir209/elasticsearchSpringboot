package com.example.demo.model;

import com.example.demo.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class MultiCriteriaSearchRequest {
    private String title;
    private String referenceGed;
    private String content;
    private String author;
    private String description;

    private String reference;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)

    private LocalDate uploadDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate dateLastUpdate;
    private Boolean folder;
    private Double size;

    private String format;

}




