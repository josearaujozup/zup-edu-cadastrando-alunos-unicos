package br.com.zup.edu.alunounico.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.alunounico.model.Aluno;
import br.com.zup.edu.alunounico.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	
	private final AlunoRepository repository;

	public AlunoController(AlunoRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AlunoDTO request, UriComponentsBuilder uriComponentsBuilder){
		
		if(repository.existsByNumeroMatriculaAndDataMatricula(request.getNumeroMatricula(),request.getDataMatricula())) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Aluno já existe no sistema");
		}
		
        Aluno aluno = request.toModel();

        repository.save(aluno);

        URI location = uriComponentsBuilder.path("/alunos/{id}")
                .buildAndExpand(aluno.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
	
	@ExceptionHandler
	public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e){
		
//		if ("UK_carro_chassi".equals(e.getConstraintName())) {
//            message = "carro com chassi já existente no sistema";
//        }
		
		
		Map<String, Object> body = Map.of(
				"message", "aluno já existente no sistema",
				"timestamp", LocalDateTime.now()
		);
		
		return ResponseEntity.unprocessableEntity().body(body);
	}
	
}
