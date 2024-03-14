package com.patel.LoanSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patel.LoanSystem.entity.PersonalInfo;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {

	PersonalInfo findByUserId(Long userId);

}