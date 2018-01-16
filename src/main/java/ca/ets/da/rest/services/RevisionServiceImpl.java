package ca.ets.da.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.ets.da.rest.model.Revision;
import ca.ets.da.rest.repositories.RevisionPredicateBuilder;
import ca.ets.da.rest.repositories.RevisionRepository;

@Service
public class RevisionServiceImpl implements RevisionService 
{
	private RevisionRepository revisionRepository;

    @Autowired
    public void setRevisionRepository(RevisionRepository changeRepository) {
        this.revisionRepository = changeRepository;
    }
    
    @Override	
	public Iterable<Revision> listAllRevisions()
	{
		return revisionRepository.findAll();		
	}
	
	@Override
	public Revision getRevisionById(Integer id)
	{
		return revisionRepository.findOne(id);
	}

	@Override
	public Iterable<Revision> listAllRevisionsByChangeId(Integer id) {
		
		RevisionPredicateBuilder predicatesBuilder = new RevisionPredicateBuilder()
	      .with("changeId", ":", id);
		
		return revisionRepository.findAll(predicatesBuilder.build());
	}
}
