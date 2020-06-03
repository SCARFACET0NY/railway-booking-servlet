package com.anton.railway.booking.repository.entity;

import com.anton.railway.booking.repository.entity.enums.WagonClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class WagonType {
    private Long wagonTypeId;
    private WagonClass wagonClass;
    private Integer capacity;
    private BigDecimal priceCoefficient;
}
