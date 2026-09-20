package com.odji.spring_back_end.git;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Résultat d'une commande Git exécutée par le backend.
 *
 * ⚠️ ATTENTION SÉCURITÉ :
 * Cette classe NE DOIT PAS exposer de commandes Git arbitraires côté API REST.
 * Toute commande exécutée doit être whitelistée (git status, git pull, etc.)
 * et jamais construite à partir d'une saisie utilisateur.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitCommandResult {

    private String command;
    private String output;
    private int exitCode;

    public boolean isSuccess() {
        return exitCode == 0;
    }
}
