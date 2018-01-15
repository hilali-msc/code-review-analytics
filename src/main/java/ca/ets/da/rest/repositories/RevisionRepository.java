package ca.ets.da.rest.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.ets.da.rest.model.Revision;

public interface RevisionRepository extends CrudRepository<Revision, Integer> {

}
