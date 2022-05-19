package com.pragma.monolito.service.mongosql;

import com.pragma.monolito.document.Image;
import com.pragma.monolito.dto.ClientDTO;
import com.pragma.monolito.exception.ResourceNotFoundException;
import com.pragma.monolito.model.mongosql.ClientImage;
import com.pragma.monolito.repository.mongosql.ClientImageRepository;
import com.pragma.monolito.repository.mongosql.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientImageServiceTest {

    @Mock
    private ImageRepository imageRepository;
    @Mock
    private ClientImageRepository clientImageRepository;
    @InjectMocks
    private ClientImageService clientImageService;
    private Image image;
    private ClientImage clientImage;
    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientImage = new ClientImage(1515, "Anderson", "Villa", "Bello", "CC", "24");
        image = new Image(1515, "prueba.png");
        clientDTO = new ClientDTO(1515, "Anderson", "Villa", "Bello", "CC", "24", "prueba.png");
    }
    @Test
    void createCliente() {
        when(clientImageRepository.save(any(ClientImage.class))).thenReturn(clientImage);
        assertEquals("Anderson", clientImageService.createCliente(clientDTO).getFirstName() );
        ClientImage clientCreated = clientImageService.createCliente(clientDTO);
        assertTrue(clientCreated.getCity().equalsIgnoreCase(clientDTO.getCity()));
        assertNotNull(clientImageService.createCliente(clientDTO));
    }
    @Test
    void getClientById() {
        when(clientImageRepository.findById(clientImage.getId())).thenReturn(Optional.of(clientImage));
        when(clientImageRepository.findByid(clientImage.getId())).thenReturn(clientImage);
        when(imageRepository.findByid(image.getId())).thenReturn(image);
        assertNotNull(clientImageService.getClientById(clientImage.getId()));
    }
    @Test
    void updateClient() {
        when(clientImageRepository.findById(clientImage.getId())).thenReturn(Optional.of(clientImage));
        when(clientImageRepository.findByid(clientImage.getId())).thenReturn(clientImage);
        ClientDTO clientDTOToUpdate = clientDTO;
        clientDTOToUpdate.setFirstName("Estiven");
        clientDTOToUpdate.setLastName("Sierra");
        when(clientImageRepository.save(any(ClientImage.class))).thenReturn(clientImage);
        assertEquals("Estiven", clientImageService.updateClient(clientDTO.getId(),clientDTOToUpdate).getFirstName());
        assertEquals("Sierra", clientImageService.updateClient(clientDTO.getId(),clientDTOToUpdate).getLastName());
        assertNotNull(clientImageService.updateClient(clientDTO.getId(),clientDTOToUpdate));

    }
    @Test
    void getClients() {
        when(clientImageRepository.findAll()).thenReturn(Arrays.asList(clientImage));
        assertNotNull(clientImageService.getClients());
        assertEquals(clientImageService.getClients(), clientImageRepository.findAll());
    }
    @Test
    void badTest()  {
        when(clientImageRepository.findById(clientImage.getId())).thenReturn(Optional.empty());
        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> {
            clientImageService.getClientById(clientImage.getId());
        });
        assertEquals("No se encuentra el cliente con el siguiente ID : -> " + clientImage.getId(), resourceNotFoundException.getMessage());
    }
    @Test
    void deteleClients() {
        when(clientImageRepository.findById(clientImage.getId())).thenReturn(Optional.of(clientImage));
        clientImageService.deteleClients(clientImage.getId());
        verify(clientImageRepository, times(1)).deleteById(clientImage.getId());
    }
}