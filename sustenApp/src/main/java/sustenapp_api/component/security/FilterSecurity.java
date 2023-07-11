package sustenapp_api.component.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.repository.UsuarioRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterSecurity extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UsuarioRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);

        if(token != null){
            UserDetails user = userRepository.findByEmail(tokenService.validateToken(token)).orElseThrow(
                    () -> new ExceptionGeneric("", "", 400)
            );

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }
}
