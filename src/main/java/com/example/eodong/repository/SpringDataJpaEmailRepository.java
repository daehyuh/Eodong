package com.example.eodong.repository;

import com.example.eodong.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SpringDataJpaEmailRepository extends JpaRepository<Email,Long>, EmailRepository {

}