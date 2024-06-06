package com.blogpeliculas.security;

import com.blogpeliculas.persistencia.enums.ERol;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_"+ERol.ADMIN.name()));
        if (isAdmin) {
            setDefaultTargetUrl("/admin/panel");
        } else {
            setDefaultTargetUrl("/home");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
