package com.alexeiboriskin.sbtask.dataserver.services;

import com.alexeiboriskin.sbtask.dataserver.models.Role;
import com.alexeiboriskin.sbtask.dataserver.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import static org.springframework.data.domain.ExampleMatcher.matching;

@Slf4j
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository userRepository) {
        this.roleRepository = userRepository;
    }

    public Role saveRole(Role role) {
        Role exampleRole = new Role();
        exampleRole.setRole(role.getRole());
        ExampleMatcher matcher =
                matching().withIgnoreNullValues().withIgnorePaths("id").withMatcher("role", GenericPropertyMatcher::exact);
        Role roleInDb = roleRepository.findOne(Example.of(exampleRole,
                matcher)).orElse(null);

        if (roleInDb != null && roleInDb.getId() != role.getId()) {
            log.info("Role already exists!");
            return roleInDb;
        }
        return roleRepository.save(role);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public List<Role> listAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public Role findByRoleName(String role) {
        Role exampleRole = new Role();
        exampleRole.setRole(role);
        ExampleMatcher matcher =
                matching().withIgnoreNullValues().withIgnorePaths("id").withMatcher("role", GenericPropertyMatcher::exact);

        return roleRepository.findOne(Example.of(exampleRole, matcher)).orElse(null);
    }
}
