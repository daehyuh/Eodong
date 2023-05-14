package com.example.eodong.repository;

import com.example.eodong.domain.Email;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmailRepository {
    Email save(Email email);
    List<Email> findAll();
    Optional<Email> findTopByEmailMailAndEmailNumberOrderByEmailDate(String email, String number);
}