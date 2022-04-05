package com.scnsoft.cocktails.dao;

import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.scnsoft.cocktails.dto.SetSearch;
import com.scnsoft.cocktails.entity.AbstractEntity;
import com.scnsoft.cocktails.entity.Set;
import com.scnsoft.cocktails.entity.User;

public class SetSpecifications {
	
	public static Specification<Set> empty() {
	    return new Specification<Set>() {
	      public Predicate toPredicate(Root<Set> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.conjunction();
	      }
	    };
	  }
	
	public static Specification<Set> idEquals(UUID id) {
	    return new Specification<Set>() {
	      public Predicate toPredicate(Root<Set> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.equal(root.get(AbstractEntity.Fields.id), id);
	      }
	    };
	  }
	
	public static Specification<Set> setsForBarmen(UUID id) {
	    return new Specification<Set>() {
	      public Predicate toPredicate(Root<Set> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.equal(root.get(Set.Fields.owner).get(AbstractEntity.Fields.id), id);
	      }
	    };
	  }

	public static Specification<Set> setsForUser(User user) {
	    return new Specification<Set>() {
	      public Predicate toPredicate(Root<Set> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//	    	  Join<Set, User> users = root.join(Set.Fields.users);
	        return cb.isMember(user, root.get(Set.Fields.users));
	      }
	    };
	  }
	
	public static Specification<Set> dateEquals(SetSearch search) {
	    return new Specification<Set>() {
	      public Predicate toPredicate(Root<Set> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	    	return cb.equal(root.get(Set.Fields.date), search.getDate());
	      }
	    };
	  }

	public static Specification<Set> statusEquals(SetSearch search) {
		return new Specification<Set>() {
		  public Predicate toPredicate(Root<Set> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		  	return cb.equal(root.get(Set.Fields.status), search.getStatus());
		  }
		};
	}
}
