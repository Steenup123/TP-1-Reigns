package main;

import reigns.Jauges.Jauge;
import reigns.Jauges.TypeJauge;
import reigns.Personnage.Personnage;

import java.util.HashMap;

public class GestionCondition {
    public TypeJauge typeJauge;
    public int number;
    public TypeCondition condition;

    public GestionCondition(TypeJauge typeJauge, int number, TypeCondition condition) {
        this.typeJauge = typeJauge;
        this.number = number;
        this.condition = condition;
    }

    public boolean hasConditionRespect(Personnage personnage) {
        for (HashMap.Entry<String, Jauge> jauge : personnage.getJauges().entrySet()) {
            if (jauge.getValue().getType() == getTypeJauge()) {
                switch (getCondition()){
                    case INFERIEUR:
                        if(jauge.getValue().getValeur() < getNumber()){
                            return true;
                        }
                        return false;
                    case SUPPERIEUR:
                        if(jauge.getValue().getValeur() > getNumber()){
                            return true;
                        }
                        return false;
                    case INFERIEUR_EGALE:
                        if(jauge.getValue().getValeur() <= getNumber()){
                            return true;
                        }
                        return false;
                    case SUPPERIEUR_EGALE:
                        if(jauge.getValue().getValeur() >= getNumber()){
                            return true;
                        }
                        return false;
                    default:
                        return false;
                }
            }
        }
        return false;
    }

    public TypeJauge getTypeJauge() {
        return typeJauge;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TypeCondition getCondition() {
        return condition;
    }

}
