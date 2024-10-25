package spring.dev.quanlithuctap.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.dev.quanlithuctap.entity.Role;
import spring.dev.quanlithuctap.entity.User;
import spring.dev.quanlithuctap.exception.InactiveUserException;
import spring.dev.quanlithuctap.repository.UserRepository;

import java.io.NotActiveException;
import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if(!user.getActive()){
                throw new InactiveUserException("User is inactive. Please contact your administrator.");
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRole()));
        } else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }
}