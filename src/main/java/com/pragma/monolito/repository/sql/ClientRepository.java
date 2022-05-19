package com.pragma.monolito.repository.sql;

import com.pragma.monolito.model.sql.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
