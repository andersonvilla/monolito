package com.pragma.monolito.dto;

import com.pragma.monolito.model.mongosql.ClientImage;
import org.modelmapper.ModelMapper;

public class GenericMdelMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    private static GenericMdelMapper instance;

    private GenericMdelMapper(){
    }
    public static GenericMdelMapper singleInstance(){
        if (instance == null){
            instance = new GenericMdelMapper();
        }
        return  instance;
    }
    public ClientDTO entityToDTO(ClientImage clientImage){
        return modelMapper.map(clientImage, ClientDTO.class);
    }
    public ClientImage DTOToEntity(ClientDTO clientDTO){
        return modelMapper.map(clientDTO, ClientImage.class);
    }




}
