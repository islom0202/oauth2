package uz.oauth2.oauth2.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.oauth2.oauth2.Entity.UserEntity;
import uz.oauth2.oauth2.Service.UserEntityService;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class LoginResource {

    private final UserEntityService userEntityService;

    public LoginResource(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/login")
    public ResponseEntity getUser(Principal principal) {
        if (principal instanceof AbstractAuthenticationToken) {
     UserEntity user = userEntityService.getAuthories((AbstractAuthenticationToken) principal);
            return ResponseEntity.ok("Congrats "+user.getGivenName());
        }
        else
            throw new IllegalArgumentException("error");
    }
}
