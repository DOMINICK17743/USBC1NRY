// 代码生成时间: 2025-10-10 03:56:46
 * It provides endpoints for skill management and certification.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SkillCertificationPlatform {
    
    public static void main(String[] args) {
        SpringApplication.run(SkillCertificationPlatform.class, args);
    }
}

/**
 * SkillService.java
 * 
 * Service class for managing skills.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;
    
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
    
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElseThrow(\/>"Skill with id \${id} not found."
    }
    
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }
    
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}

/**
 * SkillRepository.java
 * 
 * Repository interface for Skill entity.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}

/**
 * SkillController.java
 * 
 * Controller class for handling HTTP requests related to skills.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    
    @Autowired
    private SkillService skillService;
    
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }
    
    @GetMapping(