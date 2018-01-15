package ca.ets.da.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.ets.da.rest.model.File;
import ca.ets.da.rest.repositories.FileRepository;

@Service
public class FileServiceImpl implements FileService 
{
	private FileRepository fileRepository;

    @Autowired
    public void setFileRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    
    @Override	
	public Iterable<File> listAllFiles()
	{
		return fileRepository.findAll();		
	}
	
	@Override
	public File getFileById(Integer id)
	{
		return fileRepository.findOne(id);
	}
}
