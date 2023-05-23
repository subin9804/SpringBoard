package org.subin.bootBoard.commons.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subin.bootBoard.entities.Configs;
import org.subin.bootBoard.repositories.ConfigsRepository;

@Service
@RequiredArgsConstructor
public class ConfigDeleteService {

    private final ConfigsRepository repository;

    public void delete(String code) {
        Configs configs = repository.findById(code).orElse();
        if(configs == null) {
            return;
        }

        repository.delete(configs);
        repository.flush();
    }
}
