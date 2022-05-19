package com.pragma.monolito.controller.sql;

import com.pragma.monolito.model.sql.Client;
import com.pragma.monolito.service.sql.ClientService;
import com.pragma.monolito.service.mongosql.ImageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Api( description = "Api para crear, consultar, editar y eliminar clientes", tags = {"Cliente"})
@CrossOrigin
@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "Agregar un nuevo cliente", tags = {"Cliente"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente creado"),
            @ApiResponse(code = 400, message = "Entrada inválida"),
            @ApiResponse(code = 409, message = "Ya existe un Cliente con ese ID!") })
    @PostMapping()
    ResponseEntity<Client> saveClient(@ApiParam("Cliente a crear, no puede ser vacío") @RequestBody Client client){
        Client tempClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(tempClient);
    }

    @ApiOperation(value = "Obtener toda la lista de clientes", tags = {"Cliente"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operación exitosa", response=List.class )  })
    @GetMapping()
    ResponseEntity<List<Client>> listClients(){
        ResponseEntity.ok(imageService.getImages());
        return ResponseEntity.ok(clientService.getClients());
    }

    @ApiOperation(value = "Encontrar cliente por id", notes = "Retorna un solo cliente", tags = { "Cliente" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operación exitosa", response=Client.class),
            @ApiResponse(code = 404, message = "Cliente no encontrado") })
    @GetMapping(value = "/{id}")
    ResponseEntity<Optional<Client>> listById(@ApiParam("Id del usuario a obtener, no puede ser vacío") @PathVariable ("id") long id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @ApiOperation(value = "Eliminar un cliente", tags = { "Cliente" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente eliminado"),
            @ApiResponse(code = 404, message = "Cliente no encontrado") })
    @DeleteMapping(value = "/{clientId}")
    ResponseEntity<Void> deleteClient(@PathVariable ("clientId") long clientId){
        clientService.deleteClient(clientId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Actualizar un cliente existente", tags = { "Cliente" })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cliente actualizado!"),
            @ApiResponse(code = 400, message = "Id inválido"),
            @ApiResponse(code = 404, message = "Cliente no encontrado") })
    @PutMapping("/{clientId}")
    ResponseEntity<Void> updateClient(@ApiParam("Cliente a actualizar, no puede ser vacío") @RequestBody Client client,@ApiParam("Id del cliente a actualizar") @PathVariable long clientId){
        if (clientId != client.getId()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "You cant change this");
        } else {
            clientService.createClient(client);
            return ResponseEntity.ok().build();
        }
    }
}
