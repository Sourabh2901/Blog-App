package com.sr.Entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;
    private String content;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
