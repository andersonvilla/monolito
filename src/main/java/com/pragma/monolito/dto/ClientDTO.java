package com.pragma.monolito.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String city;
    private String typeOfId;
    private String age;
    private String image;
}
