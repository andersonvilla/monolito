package com.pragma.monolito.controller.sql;

import com.pragma.monolito.model.sql.ImageSql;
import com.pragma.monolito.service.sql.ImageSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageSqlController {
    @Autowired
    private ImageSqlService imageSqlService;

    @PostMapping()
    ResponseEntity<ImageSql> saveImageSql(@RequestBody ImageSql imageSql){
        ImageSql tempImageSql = imageSqlService.createImageSql(imageSql);
        try {
            return ResponseEntity.created(new URI("/api/image"+tempImageSql.getId())).body(tempImageSql);
        } catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping()
    ResponseEntity<List<ImageSql>> getAllImageSql(){
        return ResponseEntity.ok(imageSqlService.getImageSql());
    }

}
