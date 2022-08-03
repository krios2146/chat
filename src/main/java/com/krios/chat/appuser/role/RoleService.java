package com.krios.chat.appuser.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
}
