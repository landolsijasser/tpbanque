package fr.diginamic.tpbanque.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.tpbanque.entities.Client;

public interface ICrudClient extends CrudRepository<Client, Integer>{

}
