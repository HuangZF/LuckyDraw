package com.ruyicai.draw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJson
@RooToString
@Entity
@Table(name = "prize_info")
public class PrizeInfo{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "num")
	private int num;
	
	@Column(name = "arise_probability")
	private int ariseProbability;
	
	@Column(name = "delay_probability")
	private double delayProbability;
}
