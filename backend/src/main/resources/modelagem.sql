SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS task;




/* Create Tables */

CREATE TABLE task
(
	id bigint NOT NULL AUTO_INCREMENT,
	status boolean NOT NULL,
	description varchar(200),
	creation_date datetime NOT NULL,
	update_date datetime,
	exclusion_date datetime,
	conclusion_date datetime,
	PRIMARY KEY (id),
	UNIQUE (id)
);



