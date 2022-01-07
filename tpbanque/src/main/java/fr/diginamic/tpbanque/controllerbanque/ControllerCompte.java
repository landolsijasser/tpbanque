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

import fr.diginamic.tpbanque.entities.Compte;

import fr.diginamic.tpbanque.exception.CompteNotFoundException;

import fr.diginamic.tpbanque.repository.ICrudCompte;

@RestController
@RequestMapping("api/mescomptes")
public class ControllerCompte {
	@Autowired
	ICrudCompte cc;

	public ControllerCompte() {

	}

	@GetMapping("all")
	public Iterable<Compte> getComptes() {
		return cc.findAll();
	}

	@GetMapping("{id}")
	public Optional<Compte> getCompte(@PathVariable("id") Integer pid) throws Exception {
		if (cc.findById(pid).isEmpty()) {
			String s = "Compte  non trouvé, id: " + pid + " !!";
			throw new CompteNotFoundException(s);

		}
		return cc.findById(pid);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCompte(@PathVariable("id") Integer pid)
			throws CompteNotFoundException {
		if (cc.findById(pid).isEmpty()) {
			String s = "Compte non trouvé, id: " + pid + " !!";
			throw new CompteNotFoundException(s);
		}
		cc.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Compte supprimé !");
	}

	@PutMapping("{id}")
	public Compte updateCompte(@PathVariable("id") Integer pid, @RequestBody Compte compte)
			throws CompteNotFoundException {
		if (pid != compte.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " et le compte JSON " + compte + " !!";
			throw new CompteNotFoundException(s);
		}
		if (cc.findById(pid).isEmpty()) {
			String s = "Compte non trouvée, id: " + pid + " !!";
			throw new CompteNotFoundException(s);
		}
		return cc.save(compte);
	}

	@PostMapping
	public Compte addcompte(@RequestBody Compte compte) {
		return cc.save(compte);
	}

}
