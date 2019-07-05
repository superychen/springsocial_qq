package com.cqyc.spec.domain;

import lombok.Data;

/**
 *  返回的状态码
 */
@Data
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
