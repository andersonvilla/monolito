package com.pragma.monolito.repository.mongosql;

import com.pragma.monolito.model.mongosql.ClientImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientImageRepository extends JpaRepository<ClientImage, Long> {

    ClientImage findByid(long id);

}
