package com.example.demo.repository;
import com.example.demo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserAccount extends UserAccountRepository<UserAccount,Long>{

}