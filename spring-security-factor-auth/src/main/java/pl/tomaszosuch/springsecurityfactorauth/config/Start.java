package pl.tomaszosuch.springsecurityfactorauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.tomaszosuch.springsecurityfactorauth.model.AppUser;
import pl.tomaszosuch.springsecurityfactorauth.repository.AppUserRepository;

@Configuration
public class Start {

    public Start(AppUserRepository appUserRepo, PasswordEncoder passwordEncoder) {
        AppUser appUserJanusz = new AppUser();
        appUserJanusz.setUserName("Janusz");
        appUserJanusz.setPassword(passwordEncoder.encode("Janusz123"));
        appUserJanusz.setRole("ROLE_ADMIN");
        appUserJanusz.setEnable(true);


        AppUser appUserBogdan = new AppUser();
        appUserBogdan.setUserName("Bogdan");
        appUserBogdan.setPassword(passwordEncoder.encode("Bogdan123"));
        appUserBogdan.setRole("ROLE_USER");
        appUserBogdan.setEnable(true);

        appUserRepo.save(appUserJanusz);
        appUserRepo.save(appUserBogdan);



    }
}
