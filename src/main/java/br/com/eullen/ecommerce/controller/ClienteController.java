package br.com.eullen.ecommerce.controller;

import br.com.eullen.ecommerce.dto.ClienteDto;
import br.com.eullen.ecommerce.entity.Carrinho;
import br.com.eullen.ecommerce.entity.Cliente;
import br.com.eullen.ecommerce.entity.HistoricoPedido;
import br.com.eullen.ecommerce.repository.ClienteRepository;
import br.com.eullen.ecommerce.service.ClienteService;
import br.com.eullen.ecommerce.service.HistoricoPedidoService;
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

    @Autowired
    private HistoricoPedidoService historicoPedidoService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente clienteCriado = this.clienteService.criarCliente(cliente);
        return new ResponseEntity<Cliente>(clienteCriado, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/historicoPedidos", produces = "application/json")
    public Iterable<HistoricoPedido> recuperarHistoricoPedidos(@PathVariable Long id) {
        return this.historicoPedidoService.recuperarHistoricoPedidosCliente(id);
    }

    @PostMapping(value = "/{id}/historicoPedidos", produces = "application/json")
    public ResponseEntity<HistoricoPedido> salvarHistoricoPedido(@RequestBody Carrinho carrinho, @PathVariable Long id){
        HistoricoPedido historicoPedido = this.historicoPedidoService.salvarHistoricoPedido(carrinho,id);
        return new ResponseEntity<HistoricoPedido>(historicoPedido, HttpStatus.OK);
    }

    @GetMapping(value = "/auth", produces = "application/json")
    public ResponseEntity<ClienteDto> recuperarClienteLogado() {
        Cliente clienteLogado
                = (Cliente) SecurityContextHolder
                .getContext()
                .getAuthentication  ()
                .getPrincipal();

        ClienteDto dto = new ClienteDto(clienteLogado.getId(), clienteLogado.getNome(),
                clienteLogado.getUsuario(), clienteLogado.getCarrinho().getId());
        return new ResponseEntity<ClienteDto>(dto, HttpStatus.OK);
    }
}
