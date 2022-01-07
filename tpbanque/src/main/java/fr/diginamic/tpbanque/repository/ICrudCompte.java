package fr.diginamic.tpbanque.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.tpbanque.entities.Client;
import fr.diginamic.tpbanque.entities.Compte;
import fr.diginamic.tpbanque.entities.Operation;

public interface ICrudCompte extends CrudRepository<Compte, Integer>{
	@Query("select o from Operation o  where o.compte.id = :id")
    public Iterable<Operation> findByOperation(int id);

   @Query("select c from Client c where :cpt MEMBER OF c.comptes")
    public Iterable<Client> findByLivre(Compte cpt);
}
