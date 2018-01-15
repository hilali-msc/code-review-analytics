package ca.ets.da.rest.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.ets.da.rest.model.File;

public interface FileRepository extends CrudRepository<File, Integer> {

}
