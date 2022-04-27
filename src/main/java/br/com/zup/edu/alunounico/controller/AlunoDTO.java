package br.com.zup.edu.alunounico.controller;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.edu.alunounico.model.Aluno;

public class AlunoDTO {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@CPF
    private String cpf;
	
	@NotBlank
	@Size(max = 6)
    private String numeroMatricula;
	
	@NotNull
	@PastOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataMatricula;

	public AlunoDTO(@NotBlank String nome, @NotBlank @CPF String cpf, @NotBlank @Size(max = 6) String numeroMatricula,
			@NotNull @PastOrPresent LocalDate dataMatricula) {
		this.nome = nome;
		this.cpf = cpf;
		this.numeroMatricula = numeroMatricula;
		this.dataMatricula = dataMatricula;
	}
	
	public AlunoDTO() {
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNumeroMatricula() {
		return numeroMatricula;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public Aluno toModel() {
		return new Aluno(nome, cpf, numeroMatricula, dataMatricula);
	}
	
}
