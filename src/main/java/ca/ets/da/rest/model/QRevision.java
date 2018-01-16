package ca.ets.da.rest.model;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathType;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;

public class QRevision extends EntityPathBase<Revision> {

    private static final long serialVersionUID = -815455883L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRevision address = new QRevision("revision");

    public final NumberPath<Integer> revisionId = createNumber("revisionId", Integer.class);

    public QRevision(String variable) {
        this(Revision.class, new PathMetadata(null,variable,PathType.VARIABLE), INITS);
    }

    public QRevision(Path<? extends Revision> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRevision(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRevision(PathMetadata metadata, PathInits inits) {
        this(Revision.class, metadata, inits);
    }

    public QRevision(Class<? extends Revision> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
    }

}
