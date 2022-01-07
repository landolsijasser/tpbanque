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

import fr.diginamic.tpbanque.entities.AssuranceVie;

import fr.diginamic.tpbanque.exception.AssuranceVieNotFoundException;

import fr.diginamic.tpbanque.repository.ICrudAssuranceVie;

@RestController
@RequestMapping("api/monassurancevie")
public class ControllerAssuranceVie {
	@Autowired
	ICrudAssuranceVie ca;

	public ControllerAssuranceVie() {

	}

	@GetMapping("all")
	public Iterable<AssuranceVie> getAssurancesVies() {
		return ca.findAll();
	}

	@GetMapping("{id}")
	public Optional<AssuranceVie> getAssuranceVie(@PathVariable("id") Integer pid) throws Exception {
		if (ca.findById(pid).isEmpty()) {
			String s = "Assurance vie  non trouvée, id: " + pid + " !!";
			throw new AssuranceVieNotFoundException(s);

		}
		return ca.findById(pid);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAssuranceVie(@PathVariable("id") Integer pid)
			throws AssuranceVieNotFoundException {
		if (ca.findById(pid).isEmpty()) {
			String s = "Assurance vie non trouvée, id: " + pid + " !!";
			throw new AssuranceVieNotFoundException(s);
		}
		ca.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Assurance vie supprimée !");
	}

	@PutMapping("{id}")
	public AssuranceVie updateAssuranceVie(@PathVariable("id") Integer pid, @RequestBody AssuranceVie assurancevie)
			throws AssuranceVieNotFoundException {
		if (pid != assurancevie.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " et l'assurance vie JSON " + assurancevie + " !!";
			throw new AssuranceVieNotFoundException(s);
		}
		if (ca.findById(pid).isEmpty()) {
			String s = "Assurance vie non trouvée, id: " + pid + " !!";
			throw new AssuranceVieNotFoundException(s);
		}
		return ca.save(assurancevie);
	}

	@PostMapping
	public AssuranceVie addassurancevie(@RequestBody AssuranceVie assurancevie) {
		return ca.save(assurancevie);
	}

}
