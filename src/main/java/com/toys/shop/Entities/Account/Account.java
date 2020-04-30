package com.toys.shop.Entities.Account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.toys.shop.Entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String passWord;

	@Column(name = "CREATE_TIME")
	private Date createdTime;

	@Column(name = "LAST_ACCESS")
	private Date lastAccess;

	@Column(name = "ACTIVE")
	private Integer active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	@ToString.Exclude
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Role role;
}
