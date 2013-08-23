package com.ruyicai.draw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJson
@RooToString
@Entity()
@Table(name = "PRIZECONFIG")
public class PrizeConfig{

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LEVEL")
	private String level;
	
	@Column(name = "NUM")
	private int num;
	
	@Column(name = "PROBABILITY")
	private int probability;
	
	@Column(name = "DELAY")
	private int delay;
}
