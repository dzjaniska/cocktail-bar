package com.scnsoft.cocktails.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Proxy(lazy = false)
@NoArgsConstructor
@Getter @Setter 
public class Tag {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "uuid2",
        parameters = {
            @Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        }
    )
    private UUID id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "name_id")
	private Label label;
	
	public Tag(TagDTO tagDTO) {
		id = tagDTO.getId();
		//load label from database if id present, use mapper instead of constructor
		label = new Label(tagDTO.getLabel());
	}
}
