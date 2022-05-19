package com.pragma.monolito.controller.mongosql;

import com.pragma.monolito.document.Image;
import com.pragma.monolito.service.mongosql.ImageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(description = "Api con crud básico para una imagen", tags = {"Imagen"})
@RestController
@RequestMapping("/imagen")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "Obtener toda la lista de imágenes", tags = {"Imagen"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response=List.class )  })
    @GetMapping()
    ResponseEntity<List<Image>> listClients(){
        return ResponseEntity.ok(imageService.getImages());
    }

    @ApiOperation(value = "Agregar una nueva imagen", tags = {"Imagen"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Imagen creada"),})
    @PostMapping()
    ResponseEntity<Image> saveImage(@ApiParam("Imagen a agregar, no puede ser vacío") @RequestBody Image image){
        Image tempImage = imageService.createImage(image);
        return ResponseEntity.status(HttpStatus.CREATED).body(tempImage);
    }

    @GetMapping("/{id}")
    ResponseEntity<Image> getImage(@PathVariable ("id") long id){
        return ResponseEntity.ok(imageService.findById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteImage(@PathVariable ("id") long id){
        imageService.deleteImage(id);
        return ResponseEntity.status(204).build();
    }


    @PutMapping("/{id}")
    ResponseEntity<Image> updateImage(@PathVariable ("id") long id, @RequestBody Image image){
        imageService.update(id, image);
        return ResponseEntity.ok().build();
    }

}
