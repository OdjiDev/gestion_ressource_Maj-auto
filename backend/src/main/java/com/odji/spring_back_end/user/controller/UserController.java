package com.odji.spring_back_end.user.controller;

import com.odji.spring_back_end.user.dto.UserDto;
import com.odji.spring_back_end.user.entity.User;
import com.odji.spring_back_end.user.repository.UserRepository;
import com.odji.spring_back_end.common.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")   // ⚠️ Toute la classe réservée aux ADMIN
public class UserController {

    private final UserRepository userRepository;

    // ==================== LECTURE ====================

    /**
     * GET /api/admin/users?page=0&size=20
     * Liste paginée des utilisateurs (ADMIN only).
     */
    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll(
            @PageableDefault(size = 20, sort = "email") Pageable pageable) {
        Page<UserDto> page = userRepository.findAll(pageable).map(this::toDto);
        return ResponseEntity.ok(page);
    }

    /**
     * GET /api/admin/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        User user = userRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        return ResponseEntity.ok(toDto(user));
    }

    /**
     * GET /api/admin/users/email/{email}
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User avec email " + email));
        return ResponseEntity.ok(toDto(user));
    }

    // ==================== MODIFICATION ====================

    /**
     * PUT /api/admin/users/{id}/enable
     * Active ou désactive un compte.
     */
    @PutMapping("/{id}/enable")
    public ResponseEntity<UserDto> enable(@PathVariable Integer id,
                                          @RequestParam boolean enabled) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        user.setEnabled(enabled);
        userRepository.save(user);
        log.info("User {} {} activé/désactivé", id, user.getEmail());
        return ResponseEntity.ok(toDto(user));
    }

    /**
     * PUT /api/admin/users/{id}/role
     * Change le rôle d'un utilisateur.
     */
    @PutMapping("/{id}/role")
    public ResponseEntity<UserDto> changeRole(@PathVariable Integer id,
                                              @RequestParam String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        try {
            user.setUserRole(com.odji.spring_back_end.user.entity.Role.valueOf(role.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new com.odji.spring_back_end.common.exception.BusinessException(
                    "Rôle invalide : " + role);
        }
        userRepository.save(user);
        log.info("User {} rôle changé en {}", id, role);
        return ResponseEntity.ok(toDto(user));
    }

    // ==================== SUPPRESSION ====================

    /**
     * DELETE /api/admin/users/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        userRepository.delete(user);
        log.info("User {} supprimé", id);
        return ResponseEntity.noContent().build();
    }

    // ==================== Mapper manuel ====================

    private UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userRole(user.getUserRole() != null ? user.getUserRole().name() : null)
               // .enabled(user.getEnabled())
               // .personelId(user.getPersonel() != null ? user.getPersonel().getId() : null)
              //  .createdAt(user.getCreatedAt())
                .build();
    }
}
