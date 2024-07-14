package com.musinsa.common.exception;

import com.musinsa.exception.ErrorCode;
import com.musinsa.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class CategoryException extends GlobalException {
    public CategoryException(final HttpStatus status, final ErrorCode errorCode) {
        super(status, errorCode);
    }


    public static class CategoryNotFoundException extends CategoryException {
        public CategoryNotFoundException(final CategoryErrorCode errorCode, final String category) {
            super(errorCode.getStatus(), new ErrorCode<>(errorCode.getCode(), errorCode.getMessage(), category));
        }
    }

    public static class CategoryItemNotFoundException extends CategoryException {
        public CategoryItemNotFoundException(final CategoryErrorCode errorCode, final String category) {
            super(errorCode.getStatus(), new ErrorCode<>(errorCode.getCode(), errorCode.getMessage(), category));
        }
    }

    public static class BrandCategoryItemNotFoundException extends CategoryException {
        public BrandCategoryItemNotFoundException(final CategoryErrorCode errorCode, final String brand) {
            super(errorCode.getStatus(), new ErrorCode<>(errorCode.getCode(), errorCode.getMessage(), brand));
        }
    }
}
