package com.example.jorub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jorub.domain.KakaoProfile;
import com.example.jorub.domain.OauthToken;
import com.example.jorub.domain.User;
import com.example.jorub.jwt.JwtProperties;
import com.example.jorub.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.springframework.security.config.Elements.JWT;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository; //(1)

    public OauthToken getAccessToken(String code) {

        //(2)
        RestTemplate rt = new RestTemplate();

        //(3)
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //(4)
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "29e7085b4faf123bfa0d0bbaaa5a93e7");
        params.add("redirect_uri", "http://localhost:3000/oauth/callback/kakao");
        params.add("code", code);

        //(5)
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        //(6)
        ResponseEntity<String> accessTokenResponse = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        //(7)
        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(accessTokenResponse.getBody(), OauthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return oauthToken; //(8)
    }
//
    public String saveUserAndGetToken(String token) {
        KakaoProfile profile = findProfile(token);

        User user = userRepository.findByKakaoNickname(profile.getKakao_account().getProfile().getNickname());

        if(user == null) {
            user = User.builder()
                    .kakaoId(profile.getId())
                    //(4)
                    .kakaoNickname(profile.getKakao_account().getProfile().getNickname())
                    //(5)
                    .userRole("ROLE_USER").build();

            userRepository.save(user);
        }
        return createToken(user);
    }

    public String createToken(User user) { //(2-1)

        //(2-2)
        String jwtToken = com.auth0.jwt.JWT.create()

                //(2-3)
                .withSubject(user.getKakaoNickname())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))

                //(2-4)
                .withClaim("id", user.getUserCode())
                .withClaim("nickname", user.getKakaoNickname())

                //(2-5)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken; //(2-6)
    }

    public KakaoProfile findProfile(String token) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token); //(1-4)
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //(1-5)
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers);

        //(1-6)
        // Http 요청 (POST 방식) 후, response 변수에 응답을 받음
        ResponseEntity<String> kakaoProfileResponse = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        //(1-7)
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(kakaoProfileResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoProfile;




    }

}
