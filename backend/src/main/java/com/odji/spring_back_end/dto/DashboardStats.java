package com.odji.spring_back_end.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStats {

    private long totalProduits;
    private long totalCategories;
    private long totalFournisseurs;
    private long totalFactures;
    private long totalPersonels;
    private long produitsEnRupture;
}
