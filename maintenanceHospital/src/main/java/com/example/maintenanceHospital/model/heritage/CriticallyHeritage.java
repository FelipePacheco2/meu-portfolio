package com.example.maintenanceHospital.model.heritage;

public enum CriticallyHeritage {
    EMERGENCIA, // Risco de morte imediato (Escala de segundos/minutos)
    ALTA,       // Risco grave à saúde ou interrupção de tratamento vital
    MEDIA,      // Necessário para diagnóstico e tratamento, mas suporta espera
    BAIXA       // Equipamentos de apoio, conforto ou infraestrutura básica
}