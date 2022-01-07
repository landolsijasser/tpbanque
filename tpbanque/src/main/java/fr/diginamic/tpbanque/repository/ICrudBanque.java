package fr.diginamic.tpbanque.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.tpbanque.entities.Banque;
import fr.diginamic.tpbanque.entities.Client;

public interface ICrudBanque extends CrudRepository<Banque, Integer> {

	@Query("select c from Client c  where c.banque.id = :id")
    public Iterable<Client> findByClient(int id);
}
