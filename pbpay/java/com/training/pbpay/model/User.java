package com.training.pbpay.model;

import java.time.Instant;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@NotBlank(message = "{username.not.blank}")
	private String userName;
	
	@NotBlank(message = "{password.not.blank}")
	private String password;

	@NotBlank(message = "{firstname.not.blank}")
	private String firstName;
	
	@NotBlank(message = "{lastname.not.blank}")
	private String lastName;
	
	@NotBlank(message="{mobile.not.blank}")
	@Pattern(regexp="(^$|[0-9]{10})",message = "{mobile.invalid}")
	private String mobile;
	
	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.invalid}")
	private String email;
	
	@NotBlank(message="{pancard.not.blank}")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "{pancard.invalid}")
	private String panCard;
	
	@NotBlank(message = "{aadhar.not.blank}")
	@Pattern(regexp = "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$",message = "{aadhar.invalid}")
	private String aadharCard;
	
	@NotBlank(message = "{address.not.blank}")
	private String address;
	
	private Instant created;
	
	@Column(columnDefinition = "boolean default true",nullable = false)
	private boolean enabled = true;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Account> account;

}
