package com.userManagement.userManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "addresses")
@SuperBuilder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    @NotBlank(message = "Must assign to a user")
    private Long userId;

    @Column(name = "first_line")
    @NotBlank(message = "Can't be blank")
    private String firstLine;

    @Column(name = "second_line")
    private String secondLine;

    @Column(name = "street")
    @NotBlank(message = "Can't be blank")
    private String street;

    @Column(name = "landmark")
    @NotBlank(message = "Can't be blank")
    private String landmark;

    @Column(name = "city")
    @NotBlank(message = "Can't be blank")
    private String city;

    @Column(name = "state")
    @NotBlank(message = "Can't be blank")
    private String state;

    @Column(name = "postal_code")
    @NotBlank(message = "Can't be blank")
    private String postalCode;

    @Column(name = "country")
    private String country;
}
