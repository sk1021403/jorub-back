package com.example.jorub.jwt;

public interface JwtProperties {
    String SECRET = "banana"; //(2)
    int EXPIRATION_TIME =  864000000; //(3) 토큰 만료 기간
    String TOKEN_PREFIX = "Bearer "; //(4)
    String HEADER_STRING = "Authorization"; //(5)
}
