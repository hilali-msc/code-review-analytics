package ca.ets.da.rest.services;

import ca.ets.da.rest.model.Change;

public interface ChangeService {
	Iterable<Change> listAllChanges();

	Change getChangeById(Integer id);
}