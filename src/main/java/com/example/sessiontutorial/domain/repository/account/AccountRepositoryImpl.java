package com.example.sessiontutorial.domain.repository.account;

import com.example.sessiontutorial.apinfra.exception.*;
import com.example.sessiontutorial.domain.model.Account;
import com.example.sessiontutorial.domain.model.LoginIdResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Locale;

@Component
public class AccountRepositoryImpl implements AccountRepository{

    private static final String SERVICE_NAME = "/backend/account";
    private static final String API_VERSION = "/api/v1";

    @Autowired
    MessageSource messageSource;


    @Autowired
    @Qualifier("restOperationsForAccount")
    RestOperations restOperations;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Account findByEmail(String email) throws org.terasoluna.gfw.common.exception.BusinessException{
        String endpoint = SERVICE_NAME + API_VERSION + "/account";
        try{
            return restOperations.postForObject(UriComponentsBuilder
                            .fromUriString(endpoint)
                            .queryParam("search", "")
                            .toUriString(),
                    LoginIdResource.builder().email(email).build(),
                    Account.class);
        }catch(HttpClientErrorException e){
            try {
                BusinessExceptionResponse errorResponse = objectMapper.readValue(
                        e.getResponseBodyAsString(), BusinessExceptionResponse.class);
                if (errorResponse.getCode().equals("BE0001")) {
                    String errorCode = "AC0001";
                    throw new org.terasoluna.gfw.common.exception.ResourceNotFoundException("アカウントが存在しません");
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


    @Override
    public void create(Account account){
        String endpoint = SERVICE_NAME + API_VERSION + "/account";
        URI createdResourceeUri = restOperations.postForLocation(endpoint, account);
    }

    @Override
    public void updateById(Account account) throws BusinessException{
        String endpoint = SERVICE_NAME + API_VERSION + "/account";
        try {
            restOperations.put(UriComponentsBuilder
                    .fromUriString(endpoint)
                    .queryParam("id", account.getId())
                    .toUriString(), account);
        }catch(HttpClientErrorException e){
            try {
                BusinessExceptionResponse errorResponse = objectMapper.readValue(
                        e.getResponseBodyAsString(), BusinessExceptionResponse.class);
                if (errorResponse.getCode().equals("BE0003")) {
                    String errorCode = "BE0003";
                    throw new CreditLimitOverException(errorCode, errorResponse.getMessage());
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
