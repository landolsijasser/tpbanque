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
import fr.diginamic.tpbanque.exception.ClientNotFoundException;
import fr.diginamic.tpbanque.repository.ICrudClient;

@RestController
@RequestMapping("api/mesclients")
public class ControllerClient {
	@Autowired
	ICrudClient cc;

	public ControllerClient() {

	}

	@GetMapping("all")
	public Iterable<Client> getClients() {
		return cc.findAll();
	}

	@GetMapping("{id}")
	public Optional<Client> getClient(@PathVariable("id") Integer pid) throws Exception {
		if (cc.findById(pid).isEmpty()) {
			String s = "Client non trouvé, id: " + pid + " !!";
			throw new ClientNotFoundException(s);

		}
		return cc.findById(pid);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteClient(@PathVariable("id") Integer pid) throws ClientNotFoundException {
		if (cc.findById(pid).isEmpty()) {
			String s = "Client non trouvé, id: " + pid + " !!";
			throw new ClientNotFoundException(s);
		}
		cc.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body("Client supprimé !");
	}

	@PutMapping("{id}")
	public Client updateClient(@PathVariable("id") Integer pid, @RequestBody Client client)
			throws ClientNotFoundException {
		if (pid != client.getId()) {
			String s = "Error pathvariable entre l'id : " + pid + " et le client JSON " + client + " !!";
			throw new ClientNotFoundException(s);
		}
		if (cc.findById(pid).isEmpty()) {
			String s = "Client non trouvé, id: " + pid + " !!";
			throw new ClientNotFoundException(s);
		}
		return cc.save(client);
	}

	@PostMapping
	public Client addClient(@RequestBody Client client) {
		return cc.save(client);
	}
	@GetMapping("mabanque/{id}")
    public Banque getMesBanques(@PathVariable("id") Integer pid) throws BanqueNotFoundException {
        Client client = cc.findById(pid).get();    
        if(client.getBanque() == null) {
        	
            throw new BanqueNotFoundException("Banque not found");
            
        }
        return client.getBanque();
    }
}
