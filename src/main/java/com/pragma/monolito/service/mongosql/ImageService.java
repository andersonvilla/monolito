package com.pragma.monolito.service.mongosql;

import com.pragma.monolito.document.Image;
import com.pragma.monolito.exception.EmptyData;
import com.pragma.monolito.exception.ErrorConstant;
import com.pragma.monolito.exception.NoDataFoundException;
import com.pragma.monolito.exception.ResourceNotFoundException;
import com.pragma.monolito.repository.mongosql.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image createImage(Image image){
        if (image.getImage().isEmpty()){
            throw new EmptyData(ErrorConstant.EMPTYDATA);
        }
        Image imageTemp = new Image(image.getId(), encoder(image.getImage()));
        return imageRepository.save(imageTemp);
    }

    public List<Image> getImages(){
        if (imageRepository.findAll().isEmpty()){
            throw new NoDataFoundException(ErrorConstant.NOTDATA);
        }
        return imageRepository.findAll();
    }

    public void deleteImage(long id){
        if(!imageRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        imageRepository.deleteById(id);
    }

    public Image findById(long id){
        if(!imageRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        return imageRepository.findByid(id);
    }

    public Image update(long id, Image image){
        if (image.getImage().isEmpty()){
            throw new ResourceNotFoundException(ErrorConstant.EMPTYDATA);
        }
        Image currentImage = imageRepository.findByid(id);
        currentImage.setImage(encoder(image.getImage()));
        return imageRepository.save(currentImage);
    }

    public String encoder(String data){
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data.getBytes());
    }


}
