package com.pragma.monolito.model.sql;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "city")
    private String city;
    @Column(name = "type_of_id")
    private String typeOfId;
    @Column(name = "age")
    private String age;
    @Column(name = "image")
    private String image;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageSql imageSql;

    public Client(long id, String firstName, String lastName, String city, String typeOfId, String age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.typeOfId = typeOfId;
        this.age = age;
    }
}
