package com.sr.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExternalApi {

    private int userId;
    private int id;
    private String title;
    private String body;
}
