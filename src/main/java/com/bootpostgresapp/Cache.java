package com.bootpostgresapp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cache")
public class Cache implements Serializable {
	
	private static final long serialVersionUID = -1553033847835589292L;

	@Id
	@Column(name = "id")
	int id;
	
	@Column(name="name")
	String name;

	@Override
	public String toString() {
		return "Cache [id=" + id + ", name=" + name + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
