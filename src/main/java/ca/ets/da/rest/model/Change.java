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
@Table(schema="${gerrit.datasource.schema}",name="t_change")
public class Change {
	
	//Unique id of changes in database : id
	@Id
	@Column(name="id")
	private Integer id;
	
	//A combination of project name, branch name and change id : ch_Id
	@Column(name="ch_Id")
	private String changeid;
	
	//Hashed Change id in Gerrit : ch_changeId
	@Column(name="ch_changeId")
	private String hashedchangeid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChangeId() {
		return changeid;
	}

	public void setChangeId(String changeId) {
		this.changeid = changeId;
	}

	public String getHashedChangeId() {
		return hashedchangeid;
	}

	public void setHashedChangeId(String hashedChangeId) {
		this.hashedchangeid = hashedChangeId;
	}
}
