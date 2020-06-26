package br.com.model.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "partner", schema="public")
public class Partner {
	
	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="partner_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	private Integer id;
	private String name;
	private String photo;
	private String redirect;
	private Integer cashback;

}
