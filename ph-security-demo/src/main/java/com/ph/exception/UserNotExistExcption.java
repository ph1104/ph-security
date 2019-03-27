package com.ph.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotExistExcption extends RuntimeException {

    private int code;

    private String msg;

    private Object dataResult;


}
