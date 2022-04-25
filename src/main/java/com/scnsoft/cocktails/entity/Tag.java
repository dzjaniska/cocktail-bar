package com.scnsoft.cocktails.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.scnsoft.cocktails.dto.TagDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Proxy(lazy = false)
@NoArgsConstructor
@Getter @Setter 
public class Tag extends AbstractEntity {
	
	@OneToOne//(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "name_id")
	private Label label;
	
	@ManyToMany(mappedBy = "tags")
//	@JoinTable(name = "cocktail_tag", joinColumns = @JoinColumn(name = "tag_id"), inverseJoinColumns = @JoinColumn(name = "cocktail_id"))
	private List<Cocktail> cocktails = new ArrayList<>();
	
	public Tag(TagDTO tagDTO) {
		id = tagDTO.getId();
		label = new Label(tagDTO.getLabel());
	}
	
	public Tag(UUID id) {
		this.id = id;
	}
}
