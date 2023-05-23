package org.subin.bootBoard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subin.bootBoard.entities.Configs;

public interface ConfigsRepository extends JpaRepository<Configs, String> {
}
