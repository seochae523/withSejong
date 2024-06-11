package sejongZoo.sejongZoo.common.token.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import sejongZoo.sejongZoo.common.exception.token.*;
import sejongZoo.sejongZoo.common.exception.user.AccountNotFound;
import sejongZoo.sejongZoo.common.token.AuthTokenProvider;
import sejongZoo.sejongZoo.common.token.dto.AuthToken;
import sejongZoo.sejongZoo.common.token.dto.RefreshDto;

import sejongZoo.sejongZoo.user.domain.User;
import sejongZoo.sejongZoo.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshService {
    private final UserRepository userRepository;
    private final AuthTokenProvider authTokenProvider;

    public AuthToken refresh(RefreshDto refreshDto) throws ParseException {
        if(validation(refreshDto)) {
            String subject = getSubject(refreshDto);

            Authentication authentication = authTokenProvider.getAuthentication(refreshDto.getAccessToken());

            User findUser = userRepository.findByStudentId(subject)
                    .orElseThrow(() -> new AccountNotFound(subject));

            String role = findUser.getRole();
            List<String> roles = new ArrayList<>();

            for (String s : role.split(",")) {
                roles.add(s);
            }
            AuthToken newAuthToken = authTokenProvider.generateToken(authentication, roles);
            findUser.updateRefreshToken(newAuthToken.getRefreshToken());
            userRepository.save(findUser);
            return newAuthToken;
        }
        return null;
    }
    private boolean validation(RefreshDto refreshDto) throws ParseException {
        String refreshToken = refreshDto.getRefreshToken();
        String studentId = refreshDto.getStudentId();
        if(refreshToken == null){
            throw new RefreshTokenNotFound();
        }

        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new AccountNotFound(studentId));

        String userRefreshToken = user.getRefreshToken();
        if(!userRefreshToken.equals(refreshToken)){
            throw new IncorrectRefreshToken(refreshToken);
        }

        long now = System.currentTimeMillis();
        long expirationTime = getExpireTime(refreshToken);

        if(expirationTime - now <= 0){
            throw new TokenExpired(refreshToken);
        }
        return true;
    }
    private String decode(String refreshToken){
        Base64.Decoder decoder = Base64.getDecoder();
        String[] splitJwt = refreshToken.split("\\.");

        String payloadStr = new String(decoder.decode(splitJwt[1].getBytes()));

        return payloadStr;
    }

    private long getExpireTime(String refreshToken) throws ParseException {
        String payload = this.decode(refreshToken);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(payload);
        long expirationTime = (long) object.get("exp") * 1000;

        return expirationTime;
    }
    private String getSubject(RefreshDto refreshDto) throws ParseException {
        String refreshToken = refreshDto.getRefreshToken();
        String payload = this.decode(refreshToken);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(payload);
        String subject = object.get("sub").toString();
        return subject;
    }
}
