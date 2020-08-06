package br.com.eullen.ecommerce.service.impl;

import br.com.eullen.ecommerce.entity.Cliente;
import br.com.eullen.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Cliente cliente = this.clienteRepository.findByUsuario(usuario);
        return new ClienteDetails(cliente);
    }

    private final static class ClienteDetails extends Cliente implements UserDetails {

        private static final long serialVersionUID = 1L;

        public ClienteDetails(Cliente cliente) {
            super(cliente);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.emptyList();
        }

        @Override
        public String getUsername() {
            return this.getUsuario().toLowerCase();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public String getPassword() {
            return super.getSenha();
        }

    }

}
