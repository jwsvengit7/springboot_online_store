package com.example.stores.Repository;

import com.example.stores.Entity.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Long> {

   Optional<UsersModel> findUsersModelByEmailAndPassword(String email, String password);
   Optional<UsersModel> findUsersModelByEmail(String email);

   UsersModel findUsersModelById(Long id);


}
