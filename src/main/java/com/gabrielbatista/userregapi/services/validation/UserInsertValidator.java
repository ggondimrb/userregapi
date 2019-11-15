package com.gabrielbatista.userregapi.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.gabrielbatista.userregapi.domain.User;
import com.gabrielbatista.userregapi.dto.UserNewDTO;
import com.gabrielbatista.userregapi.repositories.UserRepository;
import com.gabrielbatista.userregapi.resources.exceptions.FieldMessage;
import com.gabrielbatista.userregapi.services.validation.utils.BR;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserNewDTO> {

	@Autowired
	private UserRepository repo;

	@Override
	public void initialize(UserInsert ann) {
	}

	  @Override public boolean isValid(UserNewDTO objDto,ConstraintValidatorContext context) { 
		  
      List<FieldMessage> list = new ArrayList<>();
	  
	  if (!BR.isValidCpf(objDto.getCpf())) { list.add(new FieldMessage("cpf", "CPF Inválido")); }
	  
	  User aux = repo.findByEmail(objDto.getEmail());
	  
	  if (aux != null) { list.add(new FieldMessage("email","E-mail existente!"));
	  }
	  
	  User aux2 = repo.findByEmail(objDto.getEmail());
	  
	  if (aux2 != null) { list.add(new FieldMessage("cpf","CPF existente!"));
	  }	  
	  
	  for (FieldMessage e : list) {
		  context.disableDefaultConstraintViolation();
		  context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()) .addConstraintViolation(); 
		  } 
	  return list.isEmpty(); 
	  }
}
