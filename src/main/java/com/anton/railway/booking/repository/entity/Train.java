package com.anton.railway.booking.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Train {
    private Long trainId;
    private Long locomotiveId;
    private String trainNumber;
}
