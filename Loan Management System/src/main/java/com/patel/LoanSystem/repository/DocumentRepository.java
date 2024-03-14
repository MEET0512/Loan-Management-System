package com.patel.LoanSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patel.LoanSystem.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
