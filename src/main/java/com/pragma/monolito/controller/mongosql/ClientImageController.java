package com.pragma.monolito.controller.mongosql;

import com.pragma.monolito.document.Image;
import com.pragma.monolito.dto.ClientDTO;
import com.pragma.monolito.model.mongosql.ClientImage;
import com.pragma.monolito.service.mongosql.ClientImageService;
import com.pragma.monolito.service.mongosql.ImageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Api(tags = {"Cliente_imagen"})
@CrossOrigin
@RestController
@RequestMapping("/clientimage")
public class ClientImageController {

    @Autowired
    private ClientImageService clientImageService;
    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "Agregar un nuevo cliente con imagen", tags = {"Cliente_imagen"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente creado"),})
    @PostMapping()
    ResponseEntity<ClientImage> saveClientImg(@ApiParam("Cliente a crear, no puede ser vacío") @RequestBody ClientDTO clientDTO) {
        ClientImage tempClient = clientImageService.createCliente(clientDTO);
        Image imageTemp = new Image(clientDTO.getId(), clientDTO.getImage());
        imageService.createImage(imageTemp);
        return ResponseEntity.status(HttpStatus.CREATED).body(tempClient);
    }

    @ApiOperation(value = "Eliminar un cliente con imagen", tags = {"Cliente_imagen"})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cliente eliminado"),})
    @DeleteMapping("/{clientImageId}")
    ResponseEntity<Void> deleteClients(@ApiParam("Id del usuario a eliminar, no puede ser vacío") @PathVariable("clientImageId") long clientImageId) {
        clientImageService.deteleClients(clientImageId);
        imageService.deleteImage(clientImageId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Obtener lista de clientes", tags = {"Cliente_imagen"})
    @GetMapping()
    ResponseEntity<List<ClientImage>> getClients() {
        return ResponseEntity.ok(clientImageService.getClients());
    }

    @ApiOperation(value = "Obtener un cliente por su ID", tags = {"Cliente_imagen"})
    @GetMapping("/{id}")
    ResponseEntity<ClientDTO> getClientById(@ApiParam("Id del usuario a obtener, no puede ser vacío") @PathVariable("id") long id) {
        return ResponseEntity.ok(clientImageService.getClientById(id));
    }

    @ApiOperation(value = "Actualizar un cliente", tags = {"Cliente_imagen"})
    @PutMapping("/{id}")
    ResponseEntity<ClientImage> updateClient(@ApiParam("Id del usuario a actualizar, no puede ser vacío")
                                             @PathVariable("id") long id,
                                             @RequestBody ClientDTO clientImage) {
        if (id != clientImage.getId()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Los id no coinciden");
        }
        Image image = new Image(id, clientImage.getImage());
        clientImageService.updateClient(id, clientImage);
        imageService.update(id, image);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
