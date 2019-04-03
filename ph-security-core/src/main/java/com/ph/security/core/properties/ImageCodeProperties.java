package com.ph.security.core.properties;

import lombok.Data;

/**
 * @author penghui
 * @date 2019\4\3 0003   11:48
 */
@Data
public class ImageCodeProperties {

    private Integer width = 67;

    private Integer height = 23;

    private Integer length = 4;

    private Integer expireIn = 60;



}
