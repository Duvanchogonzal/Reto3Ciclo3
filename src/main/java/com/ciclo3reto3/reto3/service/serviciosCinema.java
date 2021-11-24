/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ciclo3reto3.reto3.service;


import com.ciclo3reto3.reto3.repository.RepositorioCinema;
import com.ciclo3reto3.reto3.modelo.Cinema;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Duvan
 */

@Service
public class serviciosCinema {
    @Autowired
    private RepositorioCinema metodosCrud;
    
    public List<Cinema> getAll(){
        return metodosCrud.getAll();
    } 
    
    public Optional<Cinema>getCinema(int idCinema){
        return metodosCrud.getCinema(idCinema); 
    }
    
    public Cinema save (Cinema cine){
        if (cine.getId()==null){
            return metodosCrud.save(cine);

        }else{
            Optional<Cinema> evt=metodosCrud.getCinema(cine.getId());
            if (evt.isEmpty()) {
                return metodosCrud.save(cine);
            } else {
                return cine;
            }
        }
   
    }
    
    
     public Cinema update(Cinema cine){
        if(cine.getId()!=null){
            Optional<Cinema> consulta=metodosCrud.getCinema(cine.getId());
            if(!consulta.isEmpty()){
                if(cine.getName()!=null){
                    consulta.get().setName(cine.getName());
                }
                if(cine.getOwner()!=null){
                    consulta.get().setOwner(cine.getOwner());
                }
                if(cine.getCapacity()!=null){
                    consulta.get().setCapacity(cine.getCapacity());
                }
                if(cine.getDescription()!=null){
                    consulta.get().setDescription(cine.getDescription());
                }
                return metodosCrud.save(consulta.get());
            }
        }
        return cine;
    }
  
    
    
    public boolean deleteCinema (int cineId){
        Boolean aBoolean = getCinema(cineId).map(cine ->{
                metodosCrud.delete(cine);
                return true;
                
        }).orElse(false);
        return aBoolean;
    }
}


