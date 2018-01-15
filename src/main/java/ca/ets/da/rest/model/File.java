package ca.ets.da.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * See : https://github.com/kin-y/miningReviewRepo/wiki/Database-Schema
 * 
 * @author Mohammed Hilali
 *
 */

@Entity
@Table(name="t_file")
//SELECT f_fileName, f_revisionId \
//FROM t_file
public class File {
	
	//Unique file id in database : id
	@Id
	@Column(name="id")
	private Integer id;
	
	//The path and name of file : f_fileName
	@Column(name="f_fileName")
	private String fileName;
	
	//Revision id that file belongs to : f_revisionId (FK)
	@Column(name="f_revisionId")
	private Integer revId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getRevId() {
		return revId;
	}

	public void setRevId(Integer revId) {
		this.revId = revId;
	}
}
