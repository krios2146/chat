package com.krios.chat.appuser;

import com.krios.chat.exception.EmailAlreadyTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }

    public AppUser registerUser(AppUser user) {
        boolean userExists = appUserRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new EmailAlreadyTakenException("Email: " + user.getEmail() + " already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return appUserRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));
    }
}
