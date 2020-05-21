/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.sessiontutorial.domain.service.goods;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.sessiontutorial.domain.model.Goods;
import com.example.sessiontutorial.domain.repository.goods.GoodsRepository;

@Service
public class GoodsService {

    @Inject
    GoodsRepository goodsRepository;

    public Page<Goods> findByCategoryId(Integer categoryId, Pageable pageable) {

        long total = goodsRepository.countByCategoryId(categoryId);

        List<Goods> goodsList = Collections.emptyList();
        if (total > 0) {
            goodsList = goodsRepository.findPageByCategoryId(categoryId,
                    (int) pageable.getOffset(), pageable.getPageSize());
        }

        return new PageImpl<Goods>(goodsList, pageable, total);
    }

    public Goods findOne(String goodsId) {
        return goodsRepository.findById(goodsId);
    }

    public void increaseStock(Goods goods, int quantity) throws BusinessException {
        goods.setStock(goods.getStock() + quantity);
        goodsRepository.update(goods);
    }

    public void decreaseStock(Goods goods, int quantity) throws BusinessException{
        goods.setStock(goods.getStock() - quantity);
        goodsRepository.update(goods);
    }

}
