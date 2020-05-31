package com.ew.electronicwardrobe.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Cloths)实体类
 *
 * @author makejava
 * @since 2020-05-03 17:41:56
 */
@Data
public class Cloths implements Serializable {
    private static final long serialVersionUID = 189221060602276191L;

    private Integer id;

    private Integer category;

    private String imageUrl;

    private Integer userId;

    private Integer color;

    private Integer season;

    private String note;

}