package com.carmowallison.base_api.resources;

import com.carmowallison.base_api.domain.Traducao;
import com.carmowallison.base_api.dto.TraducaoDTO;
import com.carmowallison.base_api.services.TraducaoService;
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
@RequestMapping(value = "/traducoes")
public class TraducaoResource {

	@Autowired
	private TraducaoService service;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Busca todas as traduções")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TraducaoDTO>> findAll() {
		List<Traducao> list = service.findAll();
		List<TraducaoDTO> listDTO = list.stream().map(obj -> new TraducaoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Busca por uma tradução pelo seu id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TraducaoDTO> findById(@PathVariable String id) {
		Traducao obj = service.findById(id);
		return ResponseEntity.ok().body(new TraducaoDTO(obj));
	}


	@ApiOperation(value = "insere uma nova tradução")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody TraducaoDTO objDTO) {

		Traducao obj = new Traducao().fromDTO(objDTO);

		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Atualiza uma tradução")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody Traducao obj) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Deleta uma tradução")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
