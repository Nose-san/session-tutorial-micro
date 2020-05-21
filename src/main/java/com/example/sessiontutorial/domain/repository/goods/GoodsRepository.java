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
package com.example.sessiontutorial.domain.repository.goods;

import java.util.List;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.sessiontutorial.domain.model.Goods;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsRepository {

    List<Goods> findPageByCategoryId(int categoryId, int offset, int limit);

    Goods findById(String id);

    Long countByCategoryId(int categoryId);

    void update(Goods goods) throws BusinessException;
}
