package com.carmowallison.base_api.resources;

import com.carmowallison.base_api.domain.Linguagem;
import com.carmowallison.base_api.domain.User;
import com.carmowallison.base_api.dto.LinguagemDTO;
import com.carmowallison.base_api.dto.UserDTO;
import com.carmowallison.base_api.dto.UserNewDTO;
import com.carmowallison.base_api.services.LinguagemService;
import com.carmowallison.base_api.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/linguagens")
public class LinguagemResource {

	@Autowired
	private LinguagemService service;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Busca todas as linguagens")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LinguagemDTO>> findAll() {
		List<Linguagem> list = service.findAll();
		List<LinguagemDTO> listDTO = list.stream().map(obj -> new LinguagemDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Busca por uma linguagem pelo seu id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<LinguagemDTO> findById(@PathVariable String id) {
		Linguagem obj = service.findById(id);
		return ResponseEntity.ok().body(new LinguagemDTO(obj));
	}


	@ApiOperation(value = "insere uma nova linguagem")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody LinguagemDTO objDTO) {

		Linguagem obj = new Linguagem().fromDTO(objDTO);

		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Atualiza uma linguagem")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody LinguagemDTO objDTO) {
		Linguagem obj = new Linguagem().fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Deleta uma linguagem")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
