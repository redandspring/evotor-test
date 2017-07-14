package ru.redandspring.evotortest.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander Tretyakov.
 */
@Component
public class UnauthorizedHandler implements AuthenticationFailureHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String failureUrl = "/login?error";

        if ("Wrong password".equals(exception.getMessage())){
            failureUrl = "/login?error_wrong_password";
        }
        else if("Not found".equals(exception.getMessage())){
            failureUrl = "/login?error_not_found";
        }

        redirectStrategy.sendRedirect(request, response, failureUrl);
    }
}
