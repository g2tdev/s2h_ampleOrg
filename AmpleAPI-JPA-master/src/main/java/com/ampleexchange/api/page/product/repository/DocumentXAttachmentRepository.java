package com.ampleexchange.api.page.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ampleexchange.api.page.product.model.DocumentXAttachment;

public interface DocumentXAttachmentRepository extends JpaRepository<DocumentXAttachment, UUID> {

}
