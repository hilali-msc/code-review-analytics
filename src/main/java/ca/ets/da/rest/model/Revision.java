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
//SELECT id, rev_Id, rev_changeId, rev_patchSetNum \
//FROM t_revision
@Entity
@Table(schema="${gerrit.datasource.schema}",name="t_revision")
public class Revision {
	
	//Unique revision id in database : id
	@Id
	@Column(name="id")
	private Integer id;
	
	//Commit id of revision : rev_Id
	@Column(name="rev_Id")
	private String revId;
	
	//Change that the revision belongs to : rev_changeId (FK)
	@Column(name="rev_changeId")
	private Integer changeId;
	
	//Revision number in change : rev_patchSetNum (FK)
	@Column(name="rev_patchSetNum")
	private Integer revNumberInChange;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRevId() {
		return revId;
	}

	public void setRevId(String revId) {
		this.revId = revId;
	}

	public Integer getChangeId() {
		return changeId;
	}

	public void setChangeId(Integer changeId) {
		this.changeId = changeId;
	}

	public Integer getRevNumberInChange() {
		return revNumberInChange;
	}

	public void setRevNumberInChange(Integer revNumberInChange) {
		this.revNumberInChange = revNumberInChange;
	}
	
}
