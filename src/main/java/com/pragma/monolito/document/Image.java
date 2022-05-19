package com.pragma.monolito.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document( collection  = "imagen")
public class Image {

    @Id
    private long id;
    private String image;


}
