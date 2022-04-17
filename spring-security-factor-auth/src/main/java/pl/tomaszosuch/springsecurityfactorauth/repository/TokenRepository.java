package pl.tomaszosuch.springsecurityfactorauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.springsecurityfactorauth.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByValue(String value);
}
