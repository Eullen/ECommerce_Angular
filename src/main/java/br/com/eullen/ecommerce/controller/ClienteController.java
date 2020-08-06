package br.com.eullen.ecommerce.controller;

import br.com.eullen.ecommerce.dto.ClienteDto;
import br.com.eullen.ecommerce.entity.Cliente;
import br.com.eullen.ecommerce.repository.ClienteRepository;
import br.com.eullen.ecommerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente clienteCriado = this.clienteService.criarCliente(cliente);
        return new ResponseEntity<Cliente>(clienteCriado, HttpStatus.CREATED);
    }

    @GetMapping(value = "/auth", produces = "application/json")
    public ResponseEntity<ClienteDto> recuperarClienteLogado() {
        Cliente clienteLogado
                = (Cliente) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        ClienteDto dto = new ClienteDto(clienteLogado.getId(), clienteLogado.getNome(),
                clienteLogado.getUsuario(), clienteLogado.getCarrinho().getId());
        return new ResponseEntity<ClienteDto>(dto, HttpStatus.OK);
    }
}
