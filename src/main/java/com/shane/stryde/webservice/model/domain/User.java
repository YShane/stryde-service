package com.shane.stryde.webservice.model.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.shane.stryde.webservice.model.domain.embedded.EditInfo;
import com.shane.stryde.webservice.model.enums.UserRole;
import com.shane.stryde.webservice.model.enums.UserState;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Embedded
	private EditInfo editInfo = new EditInfo();

	@Column(unique = true, nullable = false)
	private String username = new String();

	@Column(unique = true, nullable = false)
	private String email = new String();

	@Column(nullable = false)
	private String password = new String();

	@Column(nullable = false)
	private String firstName = new String();

	@Column(nullable = false)
	private String lastName = new String();

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "ENUM('ACTIVE', 'INACTIVE', 'DELETED', 'LOCKED')")
	private UserState state;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('ROLE_ADMIN', 'ROLE_USER')", nullable = false)
	private UserRole userRole;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public EditInfo getEditInfo() {
		return editInfo;
	}

	public void setEditInfo(EditInfo editInfo) {
		this.editInfo = editInfo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}