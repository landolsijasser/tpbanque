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

import fr.diginamic.tpbanque.entities.LivretA;

import fr.diginamic.tpbanque.exception.LivretANotFoundException;

import fr.diginamic.tpbanque.repository.ICrudLivretA;

@RestController
@RequestMapping("api/meslivretsa")
public class ControllerLivretA {
	@Autowired
	ICrudLivretA cl;

	public ControllerLivretA() {

	}

	@GetMapping("all")
	public Iterable<LivretA> getLivretsA() {
		return cl.findAll();
	}

	@GetMapping("{id}")
	public Optional<LivretA> getLivretA(@PathVariable("id") Integer pid) throws Exception {
		if (cl.findById(pid).isEmpty()) {
			String s = "LivretA  non trouvé, id: " + pid + " !!";
			throw new LivretANotFoundException(s);

		}
		return cl.findById(pid);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteLivretA(@PathVariable("id") Integer pid)
			throws LivretANotFoundException {
		if (cl.findById(pid).isEmpty()) {
			String s = "LivretA non trouvé, id: " + pid + " !!";
			throw new LivretANotFoundException(s);
		}
		cl.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Livret A supprimé !");
	}

	@PutMapping("{id}")
	public LivretA updateLivretA(@PathVariable("id") Integer pid, @RequestBody LivretA livreta)
			throws LivretANotFoundException {
		if (pid != livreta.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " et le Livret A JSON " + livreta + " !!";
			throw new LivretANotFoundException(s);
		}
		if (cl.findById(pid).isEmpty()) {
			String s = "Livret A non trouvé, id: " + pid + " !!";
			throw new LivretANotFoundException(s);
		}
		return cl.save(livreta);
	}

	@PostMapping
	public LivretA addlivreta(@RequestBody LivretA livreta) {
		return cl.save(livreta);
	}

}
