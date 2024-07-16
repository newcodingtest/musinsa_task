package com.musinsa.product.service;

import com.musinsa.product.domain.Accessory;
import com.musinsa.product.domain.Sneakers;
import com.musinsa.product.domain.Socks;
import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import com.musinsa.product.infrastructure.entity.SneakersEntity;
import com.musinsa.product.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.musinsa.common.exception.CategoryException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_BRAND_ITEM_NOT_FOUND;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class SneakersService {
    private final SneakersRepository sneakersRepository;

    public Sneakers getSneakersMinimumPrice(){
        return sneakersRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(SneakersEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Sneakers"));
    }

    public Sneakers getSneakersMaximumPrice(){
        return sneakersRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(SneakersEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Sneakers"));
    }
    public Sneakers getSneakersMinimumPriceByBrand(String brand){
        return sneakersRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(SneakersEntity::toModel)
                .orElseThrow(() -> new CategoryException.BrandCategoryItemNotFoundException(CATEGORY_BRAND_ITEM_NOT_FOUND, brand));
    }

    public void createSneakers(Sneakers sneakers){
        sneakersRepository.save(SneakersEntity
                .fromModel(sneakers));
    }

    @Transactional
    public void updateSneakers(Long id, Sneakers sneakers){
        SneakersEntity sneakersEntity = sneakersRepository.findById(id).get();
        sneakersEntity.change(sneakers);
    }

    public void deleteSneakers(Long id){
        sneakersRepository.deleteById(id);
    }

    public List<Sneakers> getAll(){
        return sneakersRepository.findAll()
                .stream().map(SneakersEntity::toModel)
                .collect(Collectors.toList());
    }
    public Sneakers findOne(Long id) {
        return sneakersRepository.findById(id).get().toModel();
    }
}
