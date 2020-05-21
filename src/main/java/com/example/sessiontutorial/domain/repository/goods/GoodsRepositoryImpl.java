package com.example.sessiontutorial.domain.repository.goods;

import com.example.sessiontutorial.apinfra.exception.BusinessException;
import com.example.sessiontutorial.apinfra.exception.BusinessExceptionResponse;
import com.example.sessiontutorial.apinfra.exception.StockShortException;
import com.example.sessiontutorial.apinfra.exception.SystemException;
import com.example.sessiontutorial.domain.model.Goods;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@Component
public class GoodsRepositoryImpl implements GoodsRepository {

    private static final String SERVICE_NAME = "/backend/goods";
    private static final String API_VERSION = "/api/v1";

    @Autowired
    MessageSource messageSource;

    @Autowired
    @Qualifier("restOperationsForGoods")
    RestOperations restOperations;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<Goods> findPageByCategoryId(int categoryId, int offset, int limit) {
        String endpoint = SERVICE_NAME + API_VERSION + "/goods";
        return Arrays.asList(restOperations.getForObject(
                UriComponentsBuilder.fromUriString(endpoint)
                        .queryParam("categoryId", categoryId)
                        .queryParam("offset", offset)
                        .queryParam("limit", limit)
                        .toUriString(),
                Goods[].class));

    }

    @Override
    public Goods findById(String id){
        String endpoint = SERVICE_NAME + API_VERSION + "/goods/{id}";

        return restOperations.getForObject(
                fromPath(endpoint).build(id).toString(),
                Goods.class);

    }

    @Override
    public Long countByCategoryId(int categoryId){
        String endpoint = SERVICE_NAME + API_VERSION + "/goods";
        ResponseEntity<List<Goods>> responseEntity = restOperations.exchange(
                UriComponentsBuilder.fromUriString(endpoint)
                .queryParam("categoryId", categoryId)
                .toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Goods>>(){});

        return Long.parseLong(responseEntity.getHeaders().getFirst("X-Total-Count"));

    }

    @Override
    public void update(Goods goods) throws BusinessException{
        String endpoint = SERVICE_NAME + API_VERSION + "/goods";
        try{
            restOperations.put(UriComponentsBuilder
                    .fromUriString(endpoint)
                    .queryParam("id", goods.getId())
                    .toUriString(), goods);
        }catch(HttpClientErrorException e){
            try {
                BusinessExceptionResponse errorResponse = objectMapper.readValue(
                        e.getResponseBodyAsString(), BusinessExceptionResponse.class);
                if (errorResponse.getCode().equals("BE0003")) {
                    String errorCode = "GD0003";
                    throw new StockShortException(errorCode, errorResponse.getMessage());
                } else {
                    String errorCode = "SE0002";
                    throw new SystemException(errorCode, messageSource.getMessage(
                            errorCode, new String[]{endpoint}, Locale.getDefault()), e);
                }
            } catch (IOException e1) {
                String errorCode = "SE0002";
                throw new SystemException(errorCode, messageSource.getMessage(
                        errorCode, new String[]{endpoint}, Locale.getDefault()), e1);
            }
        }

    }
}
