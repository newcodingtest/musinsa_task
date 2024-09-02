package com.musinsa.shop.api.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LowestPricedItems {

    public List<ItemResponse> items;

    public String totalPrice;


}
