package com.aadim.project.service.impl;// TokenBlacklistService.java
import com.aadim.project.entity.TokenBlacklist;
import com.aadim.project.repository.TokenBlacklistRepository;
import com.aadim.project.service.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;


    public void addTokenToBlacklist(String token) {
        token = token.substring(7);
        TokenBlacklist tokenBlacklist = new TokenBlacklist();
        
        tokenBlacklistRepository.save(tokenBlacklist);
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklistRepository.findById(token).isPresent();
    }
}