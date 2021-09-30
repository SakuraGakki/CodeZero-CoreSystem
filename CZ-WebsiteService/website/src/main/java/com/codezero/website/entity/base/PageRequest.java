package com.codezero.website.entity.base;

import lombok.Data;

@Data
public class PageRequest {
    private Long current;
    private Long size;
}
