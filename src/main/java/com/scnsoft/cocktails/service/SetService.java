package com.scnsoft.cocktails.service;

import java.util.Arrays;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.SetRepository;
import com.scnsoft.cocktails.dao.SetSpecifications;
import com.scnsoft.cocktails.dao.UserRepository;
import com.scnsoft.cocktails.dto.SetSearch;
import com.scnsoft.cocktails.entity.Set;
import com.scnsoft.cocktails.entity.SetStatus;
import com.scnsoft.cocktails.entity.UserRole;

@Service
public class SetService {
	
	@Autowired
	private SetRepository setRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<Set> findAll(HttpSession session, SetSearch search, Pageable pageable) {
		
		Specification<Set> specification = SetSpecifications.empty();
		
		UUID id = (UUID)session.getAttribute("userId");
		UserRole role = (UserRole)session.getAttribute("userRole");
		if (role == UserRole.BARMEN)
			specification = specification.and(SetSpecifications.setsForBarmen(id));
		else if (role == UserRole.USER)
			specification = specification.and(SetSpecifications.setsForUser(userRepository.getById(id)));
		if (search.getDate() != null)
			specification = specification.and(SetSpecifications.dateEquals(search));
		if (search.getStatus() != null)
			specification = specification.and(SetSpecifications.statusEquals(search));
		
		Sort sortByDate = Sort.by(Sort.Direction.DESC, "date");
		return setRepository.findAll(specification, ((PageRequest)pageable).withSort(sortByDate));
	}

	public Set findById(HttpSession session, UUID setId) {
		
		Specification<Set> specification = SetSpecifications.idEquals(setId);
		
		UUID id = (UUID)session.getAttribute("userId");
		UserRole role = (UserRole)session.getAttribute("userRole");
		if (role == UserRole.BARMEN)
			specification = specification.and(SetSpecifications.setsForBarmen(id));
		else if (role == UserRole.USER)
			specification = specification.and(SetSpecifications.setsForUser(userRepository.getById(id)));
		
		return setRepository.findOne(specification)
				.orElseThrow();
	}

	public Set save(HttpSession session, Set set) {
		
		UUID userId = (UUID)session.getAttribute("userId");
		if ((UserRole)session.getAttribute("userRole") != UserRole.BARMEN)
			throw new UserRoleException("Only barmen can create sets");
		else {
			set.setOwner(userRepository.getById(userId));
			Hibernate.initialize(set.getOwner());
		}
		set.setStatus(SetStatus.OPENED);
		set.setId(null);
				
		return setRepository.save(set);
	}

	public Set join(HttpSession session, UUID setId, String password) {

		if ((UserRole)session.getAttribute("userRole") != UserRole.USER)
			throw new UserRoleException("Only user can join sets");
		if (setRepository.countByUserIdWhereActive((UUID)session.getAttribute("userId")) > 0)
			throw new StatusException("An active set already detected for the user");
		Set set = setRepository.getById(setId);
		if (set.getStatus() != SetStatus.ACTIVE)
			throw new StatusException("Set status must be active");
		if (!set.getPassword().equals(password))
			throw new AuthorizationException("Incorrect password");
		
		UUID userId = (UUID)session.getAttribute("userId");
		set.getUsers().add(userRepository.getById(userId));
		return setRepository.save(set);
	}

	public Set update(HttpSession session, Set set) {
		
		UUID userId = (UUID)session.getAttribute("userId");
		UserRole role = (UserRole)session.getAttribute("userRole");
		if (!Arrays.asList(UserRole.ADMIN, UserRole.BARMEN).contains(role))
			throw new UserRoleException("Only admin or barmen can update sets");
		if (role == UserRole.BARMEN && !userId.equals(set.getOwner().getId()))
			throw new UserRoleException("Only owning barmen can update sets");
		if (set.getStatus() == SetStatus.CLOSED)
			throw new StatusException("Closed sets can't be updated");

		return setRepository.save(set);
	}
	
	@Scheduled(cron = "0 0 12 * * ?")
	public void closeOld() {
		setRepository.closeWhereAgeMoreThanTwoDays();
	}

	public void delete(HttpSession session, UUID setId) {
		
		UUID userId = (UUID)session.getAttribute("userId");
		UserRole role = (UserRole)session.getAttribute("userRole");
		if (!Arrays.asList(UserRole.ADMIN, UserRole.BARMEN).contains(role))
			throw new UserRoleException("Only admin or barmen can delete sets");
		Set set = setRepository.getById(setId);
		if (role == UserRole.BARMEN && !userId.equals(set.getOwner().getId()))
			throw new UserRoleException("Only owning barmen can delete sets");
		
		setRepository.deleteById(setId);
	}

	public Set leave(HttpSession session, UUID setId, String password) {
		if ((UserRole)session.getAttribute("userRole") != UserRole.USER)
			throw new UserRoleException("Only user can leave sets");
		Set set = setRepository.getById(setId);
		if (set.getStatus() != SetStatus.ACTIVE)
			throw new StatusException("Set status must be active");
		if (!set.getPassword().equals(password))
			throw new AuthorizationException("Incorrect password");
		
		UUID userId = (UUID)session.getAttribute("userId");
		set.getUsers().remove(userRepository.getById(userId));
		return setRepository.save(set);
	}
}
