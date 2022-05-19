package com.pragma.monolito.service.sql;

import com.pragma.monolito.model.sql.ImageSql;
import com.pragma.monolito.repository.sql.ImagenSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageSqlService {

    @Autowired
    private ImagenSqlRepository imagenSqlRepository;

    public ImageSql createImageSql(ImageSql imageSql){
        return imagenSqlRepository.save(imageSql);
    }

    public List<ImageSql> getImageSql(){
        return imagenSqlRepository.findAll();
    }

    public Optional<ImageSql> findImageSqlById(long id){
        return imagenSqlRepository.findById(id);
    }

    public void deleteImageSql (long id){
        imagenSqlRepository.deleteById(id);
    }

}
