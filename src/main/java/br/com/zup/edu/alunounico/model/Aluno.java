package br.com.zup.edu.alunounico.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = {
		@UniqueConstraint(name = "UK_numero_dataMatricula", columnNames = {"numeroMatricula", "dataMatricula"})
})
@Entity
public class Aluno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String nome;
	
	@Column(nullable = false)
    private String cpf;
	
	@Column(nullable = false)
    private String numeroMatricula;
	
	@Column(nullable = false)
    private LocalDate dataMatricula;
	
	/**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
	public Aluno() {
	}

	public Aluno(String nome, String cpf, String numeroMatricula, LocalDate dataMatricula) {
		this.nome = nome;
		this.cpf = cpf;
		this.numeroMatricula = numeroMatricula;
		this.dataMatricula = dataMatricula;
	}
	
	public Long getId() {
		return id;
	}
	
}
