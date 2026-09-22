package com.design.UrlShortner.Repository;

import com.design.UrlShortner.Entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UrlRepository extends JpaRepository<URL, Long> {

    Optional<URL> findByEncodedUrl(String encodedUrl);

}
