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
package com.example.sessiontutorial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String description;

    private int categoryId;

    private int price;

    private int stock;

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Goods)) {
            return false;
        }

        return id.equals(((Goods) obj).getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
