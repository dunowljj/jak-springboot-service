package com.dunowljj.book.config.security.auth.dto;

import com.dunowljj.book.domain.user.Role;
import com.dunowljj.book.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
public class OAuthAttributes implements Serializable {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String nickname;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String nickname, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        if ("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .nickname((String) response.get("nickname"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes) {

        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .nickname((String) attributes.get("nickname"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String,Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) profile.get("nickname")) // 이름은 동의없이 못받는다.
                .nickname((String) profile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .picture((String) profile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }

    /*public void addInfoToAttributes() {
        LinkedHashMap<String, Object> newAttributes = new LinkedHashMap<>(attributes);
        if (this.name != null) {
            newAttributes.put("name", this.name);
        }

        if (this.nickname != null) {
            newAttributes.put("nickName", this.nickname);
        }

        if (this.email != null) {
            newAttributes.put("email", this.email);
        }

        this.attributes = Collections.unmodifiableMap(new LinkedHashMap<>(newAttributes));
    }*/
}
