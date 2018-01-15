package ca.ets.da.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.ets.da.rest.model.Change;
import ca.ets.da.rest.repositories.ChangeRepository;

@Service
public class ChangeServiceImpl  implements ChangeService 
{
	private ChangeRepository changeRepository;

    @Autowired
    public void setChangeRepository(ChangeRepository changeRepository) {
        this.changeRepository = changeRepository;
    }	
	
    @Override
	public Iterable<Change> listAllChanges()
	{
		return changeRepository.findAll();		
	}
	
    @Override
	public Change getChangeById(Integer id)
	{
		return changeRepository.findOne(id);
	}
}