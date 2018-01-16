package ca.ets.da.rest.services;

import ca.ets.da.rest.model.Revision;

public interface RevisionService {
	Iterable<Revision> listAllRevisions();
	
	Iterable<Revision> listAllRevisionsByChangeId(Integer id);

	Revision getRevisionById(Integer id);
}
