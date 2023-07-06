package ar.edu.unt.frc.tup.lciii.proyectoBasico.services;

import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.Match;
import ar.edu.unt.frc.tup.lciii.proyectoBasico.models.rps.MatchRps;

public class MatchFactory {
//Factoria para saber que juego debe crear, en este caso solo tenemos RPS
    public static final Match createMatch(String gameCode){

        switch (gameCode){
            case "RPS":
                return new MatchRps();
            default:
                return new MatchRps();
        }
    }
}
