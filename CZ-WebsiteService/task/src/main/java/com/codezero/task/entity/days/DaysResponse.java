package com.codezero.task.entity.days;

import lombok.Data;

@Data
public class DaysResponse {
    private String reason;
    private Integer error_code;
    private DaysResponseBody result;
}
