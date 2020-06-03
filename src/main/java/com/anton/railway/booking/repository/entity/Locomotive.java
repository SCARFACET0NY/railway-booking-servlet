package com.anton.railway.booking.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Locomotive {
    private Long locomotiveId;
    private String manufacturer;
    private String model;
    private int manufacturingYear;
}
