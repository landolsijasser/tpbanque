package fr.diginamic.tpbanque.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.tpbanque.entities.Virement;

public interface ICrudVirement extends CrudRepository<Virement, Integer> {

}
