package com.cosmos.multifamily.security;

import com.cosmos.multifamily.domain.entity.User;
import com.cosmos.multifamily.repository.UserRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *AuthFailureHandler
 *로그인 성공시 처리
 */
@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);
    private UserRepository userRepository;
    private Gson gson;

    public AuthSuccessHandler(UserRepository userRepository, Gson gson) {
        this.userRepository = userRepository;
        this.gson = gson;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("==============AuthSuccessHandler Start!!=================");
        response.setStatus(HttpServletResponse.SC_OK);
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        User user = userRepository.findUserByUserid(userDetails.getUsername());
        logger.info(user.getUserid());

        response.getWriter().print(gson.toJson(user));
        response.getWriter().flush();
    }
}
