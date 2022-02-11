package com.scnsoft.cocktails.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Proxy(lazy = false)
public class Label {
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
    @Getter @Setter 
    private UUID id;
	
	@Getter @Setter 
	private String labelEn;
	
	@Getter @Setter 
	private String labelRu;
}
