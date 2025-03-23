package com.dekra.Inicio.team.application;

public interface TeamObserver {

    void update(Long teamId, Long userId, String rolName);
}
