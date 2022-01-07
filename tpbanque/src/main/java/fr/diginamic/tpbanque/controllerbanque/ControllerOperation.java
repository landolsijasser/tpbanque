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

import fr.diginamic.tpbanque.entities.Operation;

import fr.diginamic.tpbanque.exception.OperationNotFoundException;

import fr.diginamic.tpbanque.repository.ICrudOperation;

@RestController
@RequestMapping("api/mesoperations")
public class ControllerOperation {
	@Autowired
	ICrudOperation co;

	public ControllerOperation() {

	}

	@GetMapping("all")
	public Iterable<Operation> getOperations() {
		return co.findAll();
	}

	@GetMapping("{id}")
	public Optional<Operation> getOperation(@PathVariable("id") Integer pid) throws Exception {
		if (co.findById(pid).isEmpty()) {
			String s = "Operation  non trouvée, id: " + pid + " !!";
			throw new OperationNotFoundException(s);

		}
		return co.findById(pid);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteOperation(@PathVariable("id") Integer pid)
			throws OperationNotFoundException {
		if (co.findById(pid).isEmpty()) {
			String s = "Operation non trouvée, id: " + pid + " !!";
			throw new OperationNotFoundException(s);
		}
		co.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Operation supprimée !");
	}

	@PutMapping("{id}")
	public Operation updateOperation(@PathVariable("id") Integer pid, @RequestBody Operation operation)
			throws OperationNotFoundException {
		if (pid != operation.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " et l'operation JSON " + operation + " !!";
			throw new OperationNotFoundException(s);
		}
		if (co.findById(pid).isEmpty()) {
			String s = "operation non trouvée, id: " + pid + " !!";
			throw new OperationNotFoundException(s);
		}
		return co.save(operation);
	}

	@PostMapping
	public Operation addoperation(@RequestBody Operation operation) {
		return co.save(operation);
	}

}
