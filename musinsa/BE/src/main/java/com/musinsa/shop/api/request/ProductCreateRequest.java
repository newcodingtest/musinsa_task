package com.musinsa.shop.api.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductCreateRequest {
    /**
     * 브랜드
     * */
    @NotNull(message = "브랜드 null 이어서는 안됩니다.")
    @NotEmpty(message = "브랜드 값은 빈 값이 될 수 없습니다. 값을 입력해주세요.")
    @NotBlank(message = "브랜드 값은 공백이 될 수 없습니다. 값을 입력해주세요.")
    String brand;
    /**
     * 카테고리(모자,양말..)
     * */
    @NotNull(message = "카테고리는 null 이어서는 안됩니다.")
    @NotEmpty(message = "카테고리 값은 빈 값이 될 수 없습니다. 값을 입력해주세요.")
    @NotBlank(message = "카테고리 값은 공백이 될 수 없습니다. 값을 입력해주세요.")
    String category;
    /**
     * 가격
     * */
    @NotNull(message = "가격은 null 이어서는 안됩니다.")
    @Min(0)
    BigDecimal price;
}
