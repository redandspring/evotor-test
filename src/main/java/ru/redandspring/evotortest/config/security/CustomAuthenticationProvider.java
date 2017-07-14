package ru.redandspring.evotortest.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.redandspring.evotortest.model.User;
import ru.redandspring.evotortest.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Tretyakov.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (userService == null) {
            throw new InternalAuthenticationServiceException("UserService is null");
        }

        User user = userService.getByLogin(login);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("Not found");
        }

        if (user.getPassword().equals(password)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(login, authentication.getCredentials(), grantedAuths);
        } else {
            throw new AuthenticationServiceException("Wrong password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
