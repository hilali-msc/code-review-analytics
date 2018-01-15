package ca.ets.da.rest.services;

import ca.ets.da.rest.model.File;

public interface FileService {
	Iterable<File> listAllFiles();

	File getFileById(Integer id);
}
