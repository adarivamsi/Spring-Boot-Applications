package com.adarivamsi.bike.controller;

import com.adarivamsi.bike.model.Bike;
import com.adarivamsi.bike.repository.BikeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * All Copyrights reserved @ Vamsi Charan Adari
 * Any queries mail me at adarivamsi@gmail.com
 * Created by adari on 4/30/2020
 */

@RestController
@RequestMapping("/api/v1/bikes")
public class BikesController {

    @Autowired
    private BikeRepository bikeRepository;

    @GetMapping
    public List<Bike> list() {
        return bikeRepository.findAll();
    }

    @GetMapping
    @RequestMapping(value = "{id}")
    public Bike get(@PathVariable Long id) {
        return bikeRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bike create(@RequestBody final Bike bike) {
        return bikeRepository.saveAndFlush(bike);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        bikeRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Bike update(@PathVariable Long id, @RequestBody Bike bike) {
        // TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Bike existingBike = bikeRepository.getOne(id);
        BeanUtils.copyProperties(bike, existingBike, "id");
        return bikeRepository.saveAndFlush(existingBike);
    }

}
