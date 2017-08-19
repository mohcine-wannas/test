package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Post;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.PostRepositoryCustom;

public interface PostRepository extends  CommonRepository<Post>,PostRepositoryCustom{

	
}
