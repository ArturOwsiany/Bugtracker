package com.artur.bugtracker.config;

import com.artur.bugtracker.database.entity.Person;
import com.artur.bugtracker.database.entity.Role;
import com.artur.bugtracker.database.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsDatabaseService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    private final String ROLE_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findPersonByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User details not found for the user : " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : person.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.name()));
        }

        return new User(person.getUsername(), person.getPassword(), authorities);
    }
}
