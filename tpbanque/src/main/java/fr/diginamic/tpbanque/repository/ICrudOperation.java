package fr.diginamic.tpbanque.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.tpbanque.entities.Operation;

public interface ICrudOperation extends CrudRepository<Operation, Integer> {

}
