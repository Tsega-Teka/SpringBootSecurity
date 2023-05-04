package com.SpringBootSecurity.Entity;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="Student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "Id")
	private int id;
	
	@NotBlank
	@Column(name = "f_Name")
	private String fName;
	
	@NotNull
	@Column(name = "l_Name")
	private String lName;
	
	@NotEmpty
	@Column(name = "Dep")
	private String dep;
	
	@Column(name = "Gender")
	private String gender;
}