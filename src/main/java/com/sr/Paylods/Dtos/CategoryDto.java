package com.sr.Paylods.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotBlank
    @Size(min =  4)
    private String categoryTitle;

    @NotBlank
    @Size(min = 10)
    private String categoryDescription;
}
