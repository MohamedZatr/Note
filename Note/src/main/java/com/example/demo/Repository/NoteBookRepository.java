package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Notebook;
@Repository
public interface NoteBookRepository extends JpaRepository<Notebook, Long>{
	
	List<Notebook> findByUserNotebook_Id(Long userid);
		
}
