package biz.wittkemper.stationmanager.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
public class Anrede implements Serializable{

	private static final long serialVersionUID = -8051272073300938729L;
	int id;
	String anrede;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length=20)
	public String getAnrede() {
		return this.anrede;
	}
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
	
}
