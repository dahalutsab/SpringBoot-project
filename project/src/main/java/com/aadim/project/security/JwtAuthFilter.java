package com.aadim.project.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aadim.project.dto.GlobalApiResponse;
import com.aadim.project.service.TokenBlacklistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;

    private final TokenBlacklistService tokenBlacklistService;

    public JwtAuthFilter(TokenBlacklistService tokenBlacklistService) {
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        GlobalApiResponse globalApiResponse = new GlobalApiResponse();
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtService.extractUsername(token);
            } catch (ExpiredJwtException ex) {
                log.error("ExpiredJwtException caught here: {}", ex.getMessage());

                // Create a GlobalApiResponse
                globalApiResponse.setStatus(HttpStatus.UNAUTHORIZED.toString());
                globalApiResponse.setMessage("JWT Token has expired");
                globalApiResponse.setTimestamp(LocalDateTime.now());

                // Convert the GlobalApiResponse to JSON
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                String jsonResponse = objectMapper.writeValueAsString(globalApiResponse);

                // Write the JSON response to the response output
                response.getWriter().write(jsonResponse);
                return;
            }

            // Check if the token is blacklisted
            if (tokenBlacklistService.isTokenBlacklisted(token)) {
                log.error("Token is blacklisted");

                // Create a GlobalApiResponse
                globalApiResponse.setStatus(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
                globalApiResponse.setMessage("JWT Token is Blacklisted");
                globalApiResponse.setTimestamp(LocalDateTime.now());

                // Convert the GlobalApiResponse to JSON
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                String jsonResponse = objectMapper.writeValueAsString(globalApiResponse);

                // Write the JSON response to the response output
                response.getWriter().write(jsonResponse);
                return;
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
