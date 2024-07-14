package com.musinsa.common.exception;

import org.springframework.http.HttpStatus;

public enum CategoryErrorCode {
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 카테고리입니다. 카테고리를 확인하세요.", "2000"),
    CATEGORY_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 카테고리 상품이 존재하지 않습니다.", "2001"),
    CATEGORY_BRAND_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 브랜드의 카테고리 상품이 존재하지 않습니다.", "2002"),
    ;

    private final HttpStatus status;
    private final String message;
    private final String code;

    CategoryErrorCode(final HttpStatus status, final String message, final String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
