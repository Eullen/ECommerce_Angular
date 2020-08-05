package br.com.eullen.ecommerce.controller;

import br.com.eullen.ecommerce.entity.Cliente;
import br.com.eullen.ecommerce.repository.ClienteRepository;
import br.com.eullen.ecommerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping (produces="application/json")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente clienteCriado = this.clienteService.criarCliente(cliente);
        return new ResponseEntity<Cliente>(clienteCriado, HttpStatus.CREATED);
    }

    //TODO: Remover depois e tirar a senha do JSON de retorno
    @GetMapping (value={"", "/"}, produces="application/json")
    public Iterable<Cliente> recuperarTodosOsClientes() {
        return this.clienteRepository.findAll();
    }

}
