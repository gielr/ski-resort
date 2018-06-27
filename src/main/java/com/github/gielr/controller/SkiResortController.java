package com.github.gielr.controller;

import com.github.gielr.dto.SkiResortDTO;
import com.github.gielr.dto.SkiResortFullDTO;
import com.github.gielr.model.SkiResort;
import com.github.gielr.service.SkiResortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/ski-resort")
public class SkiResortController {
    private SkiResortService skiResortService;

    @Autowired
    SkiResortController(SkiResortService skiResortService){
        this.skiResortService = skiResortService;
    }

    @GetMapping("/{id}")
    public SkiResortFullDTO findOneById(@PathVariable Long id) {
        SkiResort skiResort = skiResortService.findById(id);
        return new SkiResortFullDTO(skiResort);
    }

    @GetMapping
    public Set<SkiResortFullDTO> findAll() {
        Set<SkiResortFullDTO> result = new HashSet<>();
        Set<SkiResort> all = skiResortService.findAll();
        all.forEach(b -> result.add(new SkiResortFullDTO(b)));
        return result;
    }

    @PostMapping
    public SkiResortDTO create(@RequestBody SkiResortDTO resort) {
        SkiResort skiResort = skiResortService.createSkiResort(resort);
        return new SkiResortDTO(skiResort);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        skiResortService.deleteSkiResort(id);
    }
}
