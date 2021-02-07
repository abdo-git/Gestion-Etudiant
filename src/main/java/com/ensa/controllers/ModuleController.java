package com.ensa.controllers;

import com.ensa.entity.Module;
import com.ensa.entity.Semester;
import com.ensa.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api/module/")
public class ModuleController {


    private ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }


    @PostMapping
    public ResponseEntity<?> addModule(@RequestBody HashMap<String,Object> map){
        try {
            Module module = moduleService.addModule(map);
            return new ResponseEntity<>(module, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/list")
    public ResponseEntity<?> getAllModule(){
        return new ResponseEntity<>(moduleService.getAllModule(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getModule(@PathVariable Long id){
        try {
            Module module = moduleService.getModule(id);
            return new ResponseEntity<>(module,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteModule(@PathVariable Long id) {
        try {

            Module module = moduleService.deleteModule(id);
            return new ResponseEntity<>(module,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
