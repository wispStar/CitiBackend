package com.bondsales.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBidsVo {
    private String username;
    private Double bidvalue;
    private String ranking;
}
