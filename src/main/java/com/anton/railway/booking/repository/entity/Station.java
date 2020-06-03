package com.anton.railway.booking.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Station {
    private Long stationId;
    private String title;
    private String city;
    private String country;
    private String code;
}
