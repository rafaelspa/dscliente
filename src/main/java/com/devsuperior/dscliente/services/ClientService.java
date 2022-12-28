package com.devsuperior.dscliente.services;

import com.devsuperior.dscliente.dto.ClientDto;
import com.devsuperior.dscliente.entities.Client;
import com.devsuperior.dscliente.repositories.ClientRepository;
import com.devsuperior.dscliente.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<Client> result = clientRepository.findAll(pageable);
        return result.map(ClientDto::new);
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found")
        );
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto insert(ClientDto dto) {
        Client client = new Client();
        copyClientDtoToEntity(dto,client);
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto dto) {
        try {
            Client client = clientRepository.getReferenceById(id);
            copyClientDtoToEntity(dto, client);
            client = clientRepository.save(client);
            return new ClientDto(client);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    private static void copyClientDtoToEntity(ClientDto dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
