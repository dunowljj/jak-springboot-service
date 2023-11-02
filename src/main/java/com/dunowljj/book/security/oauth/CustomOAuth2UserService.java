package com.dunowljj.book.security.oauth;

import com.dunowljj.book.security.oauth.dto.OAuthAttributes;
import com.dunowljj.book.domain.user.User;
import com.dunowljj.book.domain.user.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRespository userRespository;
    private final HttpSession httpSession;

    // todo: 기존회원인지 가입자인지 알 수 있어야 할지도 모른다.
    // todo: custom OAuth2User 구현 혹은 Default 상속해서 필요한 값 넣기
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));

        return new OAuth2JwtUserDetails(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes
        );
    }

    // todo : update에서 권한 관련 정보도 받아오기 -> 권한 정보는 따로 조회하기
    // 따로 프로필과 닉네임을 관리한다면, 카카오톡의 프로필과 닉네임으로 계속 업데이트되면 안된다. -> map 부분 제거해야
    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRespository.findByEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRespository.save(user);
    }
}
