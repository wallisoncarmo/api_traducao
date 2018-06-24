package com.carmowallison.base_api.resources;


import com.carmowallison.base_api.domain.Linguagem;
import com.carmowallison.base_api.domain.Pagina;
import com.carmowallison.base_api.dto.PaginaDTO;
import com.carmowallison.base_api.services.PaginaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/paginas")
public class PaginaResource {

	@Autowired
	private PaginaService service;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Busca todas as páginas")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PaginaDTO>> findAll() {
		List<Pagina> list = service.findAll();
		List<PaginaDTO> listDTO = list.stream().map(obj -> new PaginaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Busca por uma página pelo seu id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaginaDTO> findById(@PathVariable String id) {
		Pagina obj = service.findById(id);
		return ResponseEntity.ok().body(new PaginaDTO(obj));
	}

	@ApiOperation(value = "insere uma nova página")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PaginaDTO objDTO) {

		Pagina obj = new Pagina().fromDTO(objDTO);

		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Atualiza uma página")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @PathVariable String id, @RequestBody Pagina obj) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Deleta uma página")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Buscar todas as traduções de uma página")
	@RequestMapping(value = {"/linguagem/{nome}"}, method = RequestMethod.GET)
	public ResponseEntity<List<PaginaDTO>> listAllPageByLinguagem(@PathVariable String nome) {
		List<Pagina> list = service.findAllPageByLinguagem(nome);
		List<PaginaDTO> listDTO = list.stream().map(obj -> new PaginaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation(value = "Insere uma traduções de uma página via csv")
	@RequestMapping(value = "traducao/pagina/csv", method = RequestMethod.POST)
	public Pagina saveCsv(@RequestParam MultipartFile file, @PathVariable Pagina pagina, @PathVariable Linguagem linguagem) {
		return service.saveCSV(file, pagina, linguagem);
	}


}
