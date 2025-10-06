// 代码生成时间: 2025-10-07 03:33:28
package com.example.knowledgebasemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for Knowledge Base Management.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class KnowledgeBaseManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgeBaseManagementApplication.class, args);
    }
}

// Additional classes and configurations would be placed here

/*
 * Service Interfaces
 */
package com.example.knowledgebasemanagement.service;

import com.example.knowledgebasemanagement.model.KnowledgeBaseEntry;
import java.util.List;

public interface KnowledgeBaseService {
    List<KnowledgeBaseEntry> getAllEntries();
    KnowledgeBaseEntry getEntryById(Long id);
    KnowledgeBaseEntry createEntry(KnowledgeBaseEntry entry);
    void updateEntry(Long id, KnowledgeBaseEntry entryDetails);
    void deleteEntry(Long id);
}

/*
 * Service Implementations
 */
package com.example.knowledgebasemanagement.service.impl;

import com.example.knowledgebasemanagement.model.KnowledgeBaseEntry;
import com.example.knowledgebasemanagement.repository.KnowledgeBaseRepository;
import com.example.knowledgebasemanagement.service.KnowledgeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    @Autowired
    public KnowledgeBaseServiceImpl(KnowledgeBaseRepository knowledgeBaseRepository) {
        this.knowledgeBaseRepository = knowledgeBaseRepository;
    }

    @Override
    public List<KnowledgeBaseEntry> getAllEntries() {
        return knowledgeBaseRepository.findAll();
    }

    @Override
    public KnowledgeBaseEntry getEntryById(Long id) {
        Optional<KnowledgeBaseEntry> entry = knowledgeBaseRepository.findById(id);
        if (!entry.isPresent()) {
            throw new RuntimeException("Knowledge Base entry not found with id " + id);
        }
        return entry.get();
    }

    @Override
    public KnowledgeBaseEntry createEntry(KnowledgeBaseEntry entry) {
        return knowledgeBaseRepository.save(entry);
    }

    @Override
    public void updateEntry(Long id, KnowledgeBaseEntry entryDetails) {
        Optional<KnowledgeBaseEntry> entry = knowledgeBaseRepository.findById(id);
        if (!entry.isPresent()) {
            throw new RuntimeException("Knowledge Base entry not found with id " + id);
        }
        entry.get().update(entryDetails);
        knowledgeBaseRepository.save(entry.get());
    }

    @Override
    public void deleteEntry(Long id) {
        Optional<KnowledgeBaseEntry> entry = knowledgeBaseRepository.findById(id);
        if (!entry.isPresent()) {
            throw new RuntimeException("Knowledge Base entry not found with id " + id);
        }
        knowledgeBaseRepository.deleteById(id);
    }
}

/*
 * Repositories
 */
package com.example.knowledgebasemanagement.repository;

import com.example.knowledgebasemanagement.model.KnowledgeBaseEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBaseEntry, Long> {
}

/*
 * Model
 */
package com.example.knowledgebasemanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class KnowledgeBaseEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Additional methods like update() can be added here
}
