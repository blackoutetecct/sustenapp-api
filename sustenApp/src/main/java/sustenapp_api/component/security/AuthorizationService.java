package sustenapp_api.component.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ExceptionGeneric("", "", 400)
        );
    }
}