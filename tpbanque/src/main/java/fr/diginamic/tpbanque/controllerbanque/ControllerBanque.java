package fr.diginamic.tpbanque.controllerbanque;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.tpbanque.entities.Banque;
import fr.diginamic.tpbanque.entities.Client;
import fr.diginamic.tpbanque.exception.BanqueNotFoundException;

import fr.diginamic.tpbanque.repository.ICrudBanque;

@RestController
@RequestMapping("api/mabanque")
public class ControllerBanque {
	@Autowired
	ICrudBanque cb;

	public ControllerBanque() {

	}

	@GetMapping("all")
	public Iterable<Banque> getBanques() {
		return cb.findAll();
	}

	@GetMapping("{id}")
	public Optional<Banque> getBanque(@PathVariable("id") Integer pid) throws Exception {
		if (cb.findById(pid).isEmpty()) {
			String s = "Banque  non trouvée, id: " + pid + " !!";
			throw new BanqueNotFoundException(s);

		}
		return cb.findById(pid);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteBanque(@PathVariable("id") Integer pid)
			throws BanqueNotFoundException {
		if (cb.findById(pid).isEmpty()) {
			String s = "Banque non trouvée, id: " + pid + " !!";
			throw new BanqueNotFoundException(s);
		}
		cb.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("banque supprimée !");
	}

	@PutMapping("{id}")
	public Banque updateBanque(@PathVariable("id") Integer pid, @RequestBody Banque banque)
			throws BanqueNotFoundException {
		if (pid != banque.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " et la banque JSON " + banque + " !!";
			throw new BanqueNotFoundException(s);
		}
		if (cb.findById(pid).isEmpty()) {
			String s = "Banque non trouvée, id: " + pid + " !!";
			throw new BanqueNotFoundException(s);
		}
		return cb.save(banque);
	}

	@PostMapping
	public Banque addbanque(@RequestBody Banque banque) {
		return cb.save(banque);
	}

	@GetMapping("mesclients/{id}")
	public Iterable<Client> getbanqueByClient(@PathVariable("id") Integer pid) {
		
		return cb.findByClient(pid);
		
	}
}
