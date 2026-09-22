package com.design.UrlShortner.Repository;

import com.design.UrlShortner.Entity.TicketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketIdRepository extends JpaRepository<TicketId, Long> {
}
