package com.account.statement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.statement.entities.CheckPaidsEntity;

@Repository
public interface CheckPaidsRepository extends JpaRepository<CheckPaidsEntity, Integer> {

}
