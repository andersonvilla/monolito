package com.pragma.monolito.repository.mongosql;

import com.pragma.monolito.document.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, Long> {

    Image findByid(long id);

}
