package ca.ets.da.rest.services;

import ca.ets.da.rest.model.Revision;

public interface RevisionService {
	Iterable<Revision> listAllRevisions();

	Revision getRevisionById(Integer id);
}
