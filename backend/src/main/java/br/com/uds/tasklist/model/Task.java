package br.com.uds.tasklist.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "task")
@Getter
@Setter
public class Task extends BaseEntity {

	@Column(name = "status")
    @NotNull
    private Boolean status;
	
	@Column(name = "description")
    @NotNull
    private String description;
	
	@Column(name = "creation_date")
    private LocalDateTime creationDate;

	@Column(name = "update_date")
    private LocalDateTime updateDate;
	
	@Column(name = "exclusion_date")
    private LocalDateTime exclusionDate;
	
	@Column(name = "conclusion_date")
    private LocalDateTime conclusionDate;
	
}
