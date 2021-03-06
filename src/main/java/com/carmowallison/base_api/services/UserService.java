package com.carmowallison.base_api.services;

import java.util.List;
import java.util.Optional;

import com.carmowallison.base_api.dto.UserDTO;
import com.carmowallison.base_api.dto.UserNewDTO;
import com.carmowallison.base_api.repositoties.UserRepository;
import com.carmowallison.base_api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.carmowallison.base_api.domain.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder bc;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User obj) {
		return repository.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);

		return repository.save(newObj);
	}

	private void updateData(User newObj, User obj) {

		newObj.setActive(obj.isActive());

		if (obj.getEmail() != null) {
			newObj.setEmail(obj.getEmail());
		}
		if (obj.getName() != null) {
			newObj.setName(obj.getName());
		}
		if (obj.getSenha() != null) {
			newObj.setSenha(bc.encode(obj.getSenha()));
		}

	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail(),objDTO.isActive(), null);
	}

	public User fromDTO(UserNewDTO objDTO) {
		return new User(null, objDTO.getName(), objDTO.getEmail(), objDTO.isActive(),bc.encode(objDTO.getSenha()));
	}
}
