package com.musinsa.product.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductCreateRequest {
    /**
     * 브랜드
     * */
    @NotBlank(message = "브랜드 값은 공백이 될 수 없습니다. 값을 입력해주세요.")
    String brand;
    /**
     * 카테고리(모자,양말..)
     * */
    @NotBlank(message = "카테고리 값은 공백이 될 수 없습니다. 값을 입력해주세요.")
    String category;
    /**
     * 가격
     * */
    @NotEmpty(message = "가격 값은 공백이 될 수 없습니다. 값을 입력해주세요.")
    @Positive(message = "가격은 음수가 될 수 없습니다. 양수를 입력해주세요.")
    BigDecimal price;
}
