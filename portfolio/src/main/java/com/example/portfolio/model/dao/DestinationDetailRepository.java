package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.DestinationDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationDetailRepository extends JpaRepository<DestinationDetail, Integer> {

}
