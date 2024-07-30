package com.proteccion.apirest_proteccion.repository;

import com.proteccion.apirest_proteccion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
