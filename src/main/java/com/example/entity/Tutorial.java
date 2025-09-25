package com.example.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tutorial {
    private int id;
    private String title;
    private String author;
    private String url;
}
