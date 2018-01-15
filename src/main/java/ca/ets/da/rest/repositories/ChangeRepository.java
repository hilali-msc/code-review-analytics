package ca.ets.da.rest.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.ets.da.rest.model.Change;

public interface ChangeRepository extends CrudRepository<Change, Integer> {

}
