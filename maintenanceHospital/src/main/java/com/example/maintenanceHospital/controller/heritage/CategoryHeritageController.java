package com.example.maintenanceHospital.controller.heritage;

import com.example.maintenanceHospital.mapperObject.heritage.CategoryHeritageDTO;
import com.example.maintenanceHospital.servico.heritage.CategoryHeritageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoryHeritageController {

    @Autowired
    CategoryHeritageService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryHeritageDTO> findAll(){
        return service.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryHeritageDTO create(@RequestBody CategoryHeritageDTO dto){
        return service.create(dto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public CategoryHeritageDTO update(@RequestBody CategoryHeritageDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
