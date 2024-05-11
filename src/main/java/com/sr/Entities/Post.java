package com.sr.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name ="post_title" ,nullable = false)
    private String title;

    private String content;

    @Column(length = 1000)
    private String imageName;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

}
