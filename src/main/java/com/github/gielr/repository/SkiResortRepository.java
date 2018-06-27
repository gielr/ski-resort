package com.github.gielr.repository;

import com.github.gielr.model.SkiResort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkiResortRepository extends JpaRepository<SkiResort, Long> {
    SkiResort findOneByCity(String city);
}
