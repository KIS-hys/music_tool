package com.example.server.repository;

import com.example.server.dao.MusicDao;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepository extends CrudRepository<MusicDao, String> {
    MusicDao getByName(String name);
}
