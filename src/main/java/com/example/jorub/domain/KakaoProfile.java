package com.example.jorub.domain;

import lombok.Data;

@Data
public class KakaoProfile {

    public Long id;
    public String connected_at;
    public Properties properties;
    public KakaoAccount kakao_account;

    @Data
    public class Properties { //(1)
        public String nickname;
    }

    @Data
    public class KakaoAccount { //(2)
        public Boolean profile_nickname_needs_agreement;
        public Profile profile;

        @Data
        public class Profile {
            public String nickname;
        }
    }

}

