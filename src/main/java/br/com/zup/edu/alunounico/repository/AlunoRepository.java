package br.com.zup.edu.alunounico.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.edu.alunounico.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	public boolean existsByNumeroMatriculaAndDataMatricula(String numeroMatricula, LocalDate dataMatricula);

}
