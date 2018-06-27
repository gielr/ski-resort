package com.github.gielr.service;

import com.github.gielr.dto.SkiResortDTO;
import com.github.gielr.model.SkiResort;

import java.util.Set;

public interface SkiResortService {
    SkiResort findById(Long id);
    Set<SkiResort> findAll();
    SkiResort createSkiResort(SkiResortDTO resort);
    void deleteSkiResort(Long id);
}
