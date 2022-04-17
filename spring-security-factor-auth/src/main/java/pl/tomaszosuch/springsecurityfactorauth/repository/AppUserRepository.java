package pl.tomaszosuch.springsecurityfactorauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.springsecurityfactorauth.model.AppUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    List<AppUser> findAll();
    Optional<AppUser> findByUserName(String username);
    AppUser save(AppUser appUser);
    void deleteById(Long id);
}
