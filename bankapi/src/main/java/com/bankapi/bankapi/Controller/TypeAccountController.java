package com.bankapi.bankapi.Controller;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bankapi.bankapi.services.TypeAccountService;
import com.bankapi.bankapi.Model.TypeAccount;
@RestController
@RequestMapping("/type")
@CrossOrigin(origins= "*")
public class TypeAccountController {
    @Autowired
    public TypeAccountService typeAccountService;


    @GetMapping()
    public ArrayList<TypeAccount> getTypes(){
        return (ArrayList<TypeAccount>) typeAccountService.getTypes();
    }
    
    @PostMapping()
    public TypeAccount saveType(@RequestBody TypeAccount type){
        return typeAccountService.saveType(type);
    }
    @GetMapping(path = "/{type}")
    public Optional<TypeAccount> findType(@PathVariable String type){
        return typeAccountService.findType(type);
    }
    @DeleteMapping(path ="/{id}")
    public String DeleteById (@PathVariable Integer id){
        boolean ok = typeAccountService.DeleteById(id);
        if(ok){
            return "The type account:  " + typeAccountService.findById(id).get().getType() + " was eliminated";
        }else{
            return "Error. Is not possible elminate this type account";
        }
    }
}
