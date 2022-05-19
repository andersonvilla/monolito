package com.pragma.monolito.service.sql;

import com.pragma.monolito.exception.ErrorConstant;
import com.pragma.monolito.exception.NoDataFoundException;
import com.pragma.monolito.exception.ResourceNotFoundException;
import com.pragma.monolito.model.sql.Client;
import com.pragma.monolito.repository.sql.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client){
        return clientRepository.save(client);
    }

    public List<Client> getClients (){
            if(clientRepository.findAll().isEmpty()){
                throw new NoDataFoundException(ErrorConstant.NOTDATA);
            }
            return clientRepository.findAll();
    }

    public Optional<Client> findById(long id){
        if(!clientRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        return clientRepository.findById(id);
    }

    public void deleteClient (long id){
        if (!clientRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        clientRepository.deleteById(id);
    }


}
