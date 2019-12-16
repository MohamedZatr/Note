package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Colors;
@Repository
public interface ColorRepository extends JpaRepository<Colors, Long> {
	Colors findByColor(String color);
	boolean existsByColor(String color);
	void deleteByColor(String color);
}
