package com.apodoba.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 
 * @author arina
 *
 */

@Entity
@Table(name = "CATEGORIES")
public class CategoryEntity implements Serializable{

	private static final long serialVersionUID = 7099088069719953260L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="category_seq_gen")
	@SequenceGenerator(name="category_seq_gen", sequenceName="CATEGORIES_SEQ")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PARENT_CATEGORY_ID")
	private CategoryEntity category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null){
			return false;
		}
		if(this == other){
			return true;
		}
		if(other instanceof CategoryEntity){
			CategoryEntity otherCategory = (CategoryEntity) other;
			return new EqualsBuilder().
					append(this.name, otherCategory.name).
					append(this.description, otherCategory.description).
					append(this.category, otherCategory.category).
					isEquals();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().
				append(this.name).
				append(this.description).
				append(this.category).
				hashCode();
	}
	
}
