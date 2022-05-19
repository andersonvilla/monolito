package com.pragma.monolito.model.sql;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class ImageSql {
    @Id
    public long id;
    @Column(name = "images")
    public String image;
}
