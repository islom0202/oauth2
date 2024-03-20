package uz.oauth2.oauth2.Service;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import uz.oauth2.oauth2.Entity.UserEntity;
import uz.oauth2.oauth2.Repository.UserEntityRepository;

import java.util.Map;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    private final EmailService emailService;

    public UserEntityService(UserEntityRepository userEntityRepository, EmailService emailService) {
        this.userEntityRepository = userEntityRepository;
        this.emailService = emailService;
    }

    public UserEntity save(UserEntity user) {
        return userEntityRepository.save(user);
    }

    public UserEntity getAuthories(AbstractAuthenticationToken authToken) {
        Map<String, Object> attributes;
        if (authToken instanceof OAuth2AuthenticationToken) {
            attributes = ((OAuth2AuthenticationToken) authToken).getPrincipal().getAttributes();
        } else {
            throw new IllegalArgumentException("error");
        }
        if (((OAuth2AuthenticationToken) authToken).getAuthorizedClientRegistrationId().equals("github")){
            return saveUserByGithub(attributes);
        }
        return saveUser(attributes);
    }

    private UserEntity saveUser(Map<String, Object> attributes) {
        UserEntity user = new UserEntity();
        if (attributes.get("given_name") != null) {
            user.setGivenName((String) attributes.get("given_name"));
        }
        if (attributes.get("family_name") != null) {
            user.setFamilyName((String) attributes.get("family_name"));
        }
        if (attributes.get("email") != null) {
            user.setEmail((String) attributes.get("email"));
        }
        if (attributes.get("locale") != null) {
            user.setLocale((String) attributes.get("locale"));
        }
        if (attributes.get("email_verified") != null) {
            user.setEmailVerified((boolean) attributes.get("email_verified"));
        }
        if (attributes.get("picture") != null) {
            user.setPicture((String) attributes.get("picture"));
        }
        emailService.getRecipientEmail(user.getEmail());
        return userEntityRepository.save(user);
    }

    public UserEntity saveUserByGithub(Map<String, Object> attributes){
        UserEntity user = new UserEntity();
        if (attributes.get("login") != null){
            user.setGivenName((String) attributes.get("login"));
        }
        if (attributes.get("site_admin") != null){
            user.setEmailVerified((boolean) attributes.get("site_admin"));
        }
        if (attributes.get("url") != null){
            user.setPicture((String) attributes.get("url"));
        }
        return userEntityRepository.save(user);
    }
}
