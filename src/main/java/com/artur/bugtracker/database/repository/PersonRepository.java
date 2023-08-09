package com.artur.bugtracker.database.repository;

import com.artur.bugtracker.database.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
