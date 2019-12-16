package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Note;
import com.example.demo.model.Notebook;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	List<Note> findAllByNotebook(Notebook notebook);
	List<Note> findByUserNote_Id(Long userid);
}
