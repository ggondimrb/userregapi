package com.gabrielbatista.userregapi.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.gabrielbatista.userregapi.domain.User;
import com.gabrielbatista.userregapi.dto.UserDTO;
import com.gabrielbatista.userregapi.repositories.UserRepository;
import com.gabrielbatista.userregapi.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdate, UserDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public void initialize(UserUpdate ann) {
	}
	
	  @Override public boolean isValid(UserDTO objDto, ConstraintValidatorContext context) {
	  
	  //method to get uri parameters	  
	  @SuppressWarnings("unchecked") Map<String, String> map = (Map<String,String>)
	  request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	  Integer uriId = Integer.parseInt(map.get("id"));
	  
	  //list for inserting validation erros
	  List<FieldMessage> list = new ArrayList<>();
	  
	  User aux = repo.findByEmail(objDto.getEmail());
	  
	  if (aux != null && !aux.getId().equals(uriId)) { 
		  list.add(new FieldMessage("email","Existing Email")); 
		  }
	  
	  for (FieldMessage e : list) { 
		  context.disableDefaultConstraintViolation();
		  context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation(); 
		  } 
	  return list.isEmpty(); 
	  }
	 
}
