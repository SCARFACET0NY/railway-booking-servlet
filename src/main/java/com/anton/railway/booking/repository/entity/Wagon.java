package com.anton.railway.booking.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Wagon {
    private Long wagonId;
    private String wagonNumber;
    private Long wagonTypeId;
    private Long trainId;
}
