package pl.tomaszosuch.springsecurityfactorauth.controller;

import org.springframework.boot.Banner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.tomaszosuch.springsecurityfactorauth.model.AppUser;
import pl.tomaszosuch.springsecurityfactorauth.model.Token;
import pl.tomaszosuch.springsecurityfactorauth.repository.AppUserRepository;
import pl.tomaszosuch.springsecurityfactorauth.repository.TokenRepository;
import pl.tomaszosuch.springsecurityfactorauth.service.UserServiceImpl;

import java.security.Principal;
import java.util.Collection;

@Controller
@RequestMapping("/api")
public class UserController {

    private UserServiceImpl userService;
    private AppUserRepository appUserRepository;
    private TokenRepository tokenRepository;

    public UserController(UserServiceImpl userService, AppUserRepository appUserRepository, TokenRepository tokenRepository) {
        this.userService = userService;
        this.appUserRepository = appUserRepository;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);

        return "Hello";
    }

    @GetMapping("/sing-up")
    public String singup(Model model) {
        model.addAttribute("user", new AppUser());
        return "sing-up";
    }

    @PostMapping("/register")
    public String register(AppUser appUser) {
        userService.addUser(appUser);
        return "register";
    }

    @GetMapping("token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepository.findByValue(value);
        AppUser appUser = byValue.getAppUser();
        appUser.setEnable(true);
        appUserRepository.save(appUser);

        return "hello";
    }
}
