package com.example.jorub.repository;

import com.example.jorub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // select * from user_master where kakao_emial = ?
//    public User findByKakaoEmail(String kakaoEmail);

    public User findByUserCode(Long userCode);

    public User findByKakaoNickname(String kakaoNickname);

}
