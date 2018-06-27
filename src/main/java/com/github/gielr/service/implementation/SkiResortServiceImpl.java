package com.github.gielr.service.implementation;

import com.github.gielr.dto.SkiResortDTO;
import com.github.gielr.exceptions.ValidationError;
import com.github.gielr.exceptions.ValidationException;
import com.github.gielr.model.SkiResort;
import com.github.gielr.repository.SkiResortRepository;
import com.github.gielr.service.SkiResortService;
import com.github.gielr.service.SkiResortWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SkiResortServiceImpl implements SkiResortService {
    private SkiResortRepository skiResortRepository;
    private SkiResortWeatherService skiResortWeatherService;

    @Autowired
    public SkiResortServiceImpl(SkiResortRepository skiResortRepository, SkiResortWeatherService skiResortWeatherService) {
        this.skiResortRepository = skiResortRepository;
        this.skiResortWeatherService = skiResortWeatherService;
    }

    @Override
    public SkiResort findById(Long id) {
        return skiResortRepository.findById(id).orElseThrow(() -> new ValidationException(validateFindById(id)));
    }

    @Override
    public Set<SkiResort> findAll() {
        return new HashSet<>(skiResortRepository.findAll());
    }

    @Override
    public SkiResort createSkiResort(SkiResortDTO resort) {
        validateCreation(resort.getCity(), resort.getPrice());

        SkiResort skiResort = new SkiResort();
        skiResort.setCity(resort.getCity());
        skiResort.setPrice(resort.getPrice());

        SkiResort save = skiResortRepository.save(skiResort);
        skiResortWeatherService.createSkiResortWeather(save.getCity());
        return save;
    }

    @Override
    public void deleteSkiResort(Long id) {
        try{
            skiResortRepository.deleteById(id);
        } catch (RuntimeException e){
            validateDeleteById(id);
        }
    }

    private void validateCreation(String city, BigDecimal price) {
        List<ValidationError> errors = new ArrayList<>();
        if (city == null) {
            ValidationError error = new ValidationError("city", "May not be null");
            errors.add(error);
        } else if (city.isEmpty()) {
            ValidationError error = new ValidationError("city", "May not be null");
            errors.add(error);
        }

        if (price == null) {
            ValidationError error = new ValidationError("price", "May not be null");
            errors.add(error);
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    private List<ValidationError> validateFindById(Long id) {
        List<ValidationError> errors = new ArrayList<>();

        ValidationError error = new ValidationError("id", "Wrong id");
        errors.add(error);

        return errors;
    }

    private void validateDeleteById(Long id){
        List<ValidationError> errors = new ArrayList<>();

        ValidationError error = new ValidationError("id", "Wrong id");
        errors.add(error);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
