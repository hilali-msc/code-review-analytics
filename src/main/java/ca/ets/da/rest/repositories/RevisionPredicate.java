package ca.ets.da.rest.repositories;


import org.apache.commons.lang3.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

import ca.ets.da.rest.model.Revision;
import ca.ets.da.rest.model.SearchCriteria;

public class RevisionPredicate {
	 
    private SearchCriteria criteria;
 
    public RevisionPredicate(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public BooleanExpression getPredicate() {
        PathBuilder<Revision> entityPath = new PathBuilder<>(Revision.class, "revision");
        
        if (StringUtils.isNumeric(criteria.getValue().toString())) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case ":":
                    return path.eq(value);
                case ">":
                    return path.goe(value);
                case "<":
                    return path.loe(value);
            }
        } 
        else {
            StringPath path = entityPath.getString(criteria.getKey());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
    }
}
