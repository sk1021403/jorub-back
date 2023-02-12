package com.example.jorub.controller;

import com.example.jorub.domain.OauthToken;
import com.example.jorub.domain.User;
import com.example.jorub.jwt.JwtProperties;
import com.example.jorub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*") // 컨트롤러에서 설정
@RestController
public class UserController {

    @Autowired
    UserService userService;

    // 프론트에서 인가코드 받아오는 url : localhost:8080/api?code={code}
    @GetMapping("/api")
    @CrossOrigin(origins = "*") // 컨트롤러에서 설정
    public ResponseEntity getLogin(@RequestParam("code") String code){

        // 넘어온 인가 코드를 통해 access token 발금
        OauthToken oauthToken = userService.getAccessToken(code);

        // 발급 받은 access token으로 카카오 회원 db 저장 후 jwt 생성
        String jwtToken = userService.saveUserAndGetToken(oauthToken.getAccess_token());

        //(3)
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);

        //(4)
        return ResponseEntity.ok().headers(headers).body("success");
    }
}
