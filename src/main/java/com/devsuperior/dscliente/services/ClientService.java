package com.devsuperior.dscliente.services;

import com.devsuperior.dscliente.dto.ClientDto;
import com.devsuperior.dscliente.entities.Client;
import com.devsuperior.dscliente.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return result.map(this::clientToClientDto);
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).get();
        return clientToClientDto(client);
    }

    @Transactional
    public ClientDto insert(ClientDto dto) {
        Client client = clientRepository.save(new Client(
                dto.getId(),
                dto.getName(),
                dto.getCpf(),
                dto.getIncome(),
                dto.getBirthDate(),
                dto.getChildren()
        ));
        return clientToClientDto(client);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto dto) {
        Client client = clientRepository.getReferenceById(id);
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
        client = clientRepository.save(client);
        return clientToClientDto(client);
    }

    public ClientDto clientToClientDto(Client client) {
        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getIncome(),
                client.getBirthDate(),
                client.getChildren()
        );
    }
}
