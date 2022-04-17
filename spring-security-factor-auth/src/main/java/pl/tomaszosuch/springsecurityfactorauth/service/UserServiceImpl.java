package pl.tomaszosuch.springsecurityfactorauth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.tomaszosuch.springsecurityfactorauth.model.AppUser;
import pl.tomaszosuch.springsecurityfactorauth.model.Token;
import pl.tomaszosuch.springsecurityfactorauth.repository.AppUserRepository;
import pl.tomaszosuch.springsecurityfactorauth.repository.TokenRepository;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserServiceImpl  implements UserService {

    private final TokenRepository tokenRepository;
    private final MailService mailService;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(TokenRepository tokenRepository, MailService mailService, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        appUserRepository.save(appUser);
        sendToken(appUser);
    }

    private void sendToken(AppUser appUser) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAppUser(appUser);
        tokenRepository.save(token);
        String url = "http://localhost:8080/token?value=" + tokenValue;

        try {
            mailService.sendMail(appUser.getUsername(), "Potwierdzaj to!", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
