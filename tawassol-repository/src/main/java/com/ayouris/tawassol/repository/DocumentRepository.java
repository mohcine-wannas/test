package com.ayouris.tawassol.repository;

import org.springframework.stereotype.Repository;

import com.ayouris.tawassol.repository.custom.DocumentRepositoryCustom;

/**
 * Created by chamakh on 06/01/2017.
 */
@Repository
public interface DocumentRepository  extends  DocumentRepositoryCustom { // extends JpaRepository<Document, Long>, DocumentRepositoryCustom {

}
