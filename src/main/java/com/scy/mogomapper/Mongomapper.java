package com.scy.mogomapper;

import com.scy.pojo.mogopojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Mongomapper  extends MongoRepository<Comment, String> {

}
