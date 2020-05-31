package com.ew.electronicwardrobe.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * (Outfit)实体类
 *
 * @author makejava
 * @since 2020-05-28 00:28:15
 */
@Data
public class Outfit implements Serializable {
    private static final long serialVersionUID = 245128289811089334L;

    private Integer id;

    private Integer userId;

    private Integer season;

    private String imageUrl;

    private Integer dressup;

    private Integer temperature;

    private List<Cloths> clothsList;

}
