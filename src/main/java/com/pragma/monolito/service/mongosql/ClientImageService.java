package com.pragma.monolito.service.mongosql;

import com.pragma.monolito.document.Image;
import com.pragma.monolito.dto.ClientDTO;
import com.pragma.monolito.dto.GenericMdelMapper;
import com.pragma.monolito.exception.EmptyData;
import com.pragma.monolito.exception.ErrorConstant;
import com.pragma.monolito.exception.NoDataFoundException;
import com.pragma.monolito.exception.ResourceNotFoundException;
import com.pragma.monolito.model.mongosql.ClientImage;
import com.pragma.monolito.model.sql.Client;
import com.pragma.monolito.repository.mongosql.ClientImageRepository;
import com.pragma.monolito.repository.mongosql.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientImageService {

    @Autowired
    private ClientImageRepository clientImageRepository;
    @Autowired
    private ImageRepository imageRepository;

    static GenericMdelMapper mdelMapper = GenericMdelMapper.singleInstance();

    public ClientImage createCliente(ClientDTO clientDTO) {
        if (clientDTO.getFirstName().isEmpty() || clientDTO.getLastName().isEmpty() || clientDTO.getCity().isEmpty() || clientDTO.getTypeOfId().isEmpty() || clientDTO.getAge().isEmpty() || clientDTO.getImage().isEmpty()) {
            throw new EmptyData(ErrorConstant.EMPTYDATA);
        } else if (clientImageRepository.findById(clientDTO.getId()).isPresent()) {
            throw new EmptyData("Ya existe un usuario con el ID -> " + clientDTO.getId());
        }
        ClientImage tempClient = mdelMapper.DTOToEntity(clientDTO);
        return clientImageRepository.save(tempClient);
    }

    public ClientDTO getClientById(long id) {
        ClientImage clientImage = clientImageRepository.findById(id).orElseThrow(()->{
        throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);});
        Image image = imageRepository.findByid(id);
        ClientDTO clientDTO = mdelMapper.entityToDTO(clientImage);
        clientDTO.setImage(image.getImage());
        return clientDTO;
    }

    public List<ClientImage> getClients() {
        if (clientImageRepository.findAll().isEmpty()) {
            throw new NoDataFoundException(ErrorConstant.NOTDATA);
        }
        return clientImageRepository.findAll();
    }

    public void deteleClients(long id) {
        if (!clientImageRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        clientImageRepository.deleteById(id);
    }

    public ClientImage updateClient(long id, ClientDTO clientDTO) {
        if (clientDTO.getFirstName().isEmpty()
                || clientDTO.getLastName().isEmpty()
                || clientDTO.getCity().isEmpty()
                || clientDTO.getTypeOfId().isEmpty()
                || clientDTO.getAge().isEmpty()
                || clientDTO.getImage().isEmpty()) {
            throw new EmptyData(ErrorConstant.EMPTYDATA);
        } else if (!clientImageRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        ClientImage currentClient = clientImageRepository.findByid(id);
        currentClient.setFirstName(clientDTO.getFirstName());
        currentClient.setLastName(clientDTO.getLastName());
        currentClient.setCity(clientDTO.getCity());
        currentClient.setTypeOfId(clientDTO.getTypeOfId());
        currentClient.setAge(clientDTO.getAge());
        return clientImageRepository.save(currentClient);

    }

}
