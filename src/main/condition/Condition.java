package main;



import java.util.ArrayList;
import java.util.List;

public class Conditions {

        private List<GestionCondition> conditions;

        public Conditions() {
            this.conditions = new ArrayList<>();
        }

    public boolean hasConditionsRespect(Personnage personnage) {
        for (GestionCondition condition : getConditions()) {
            if(!condition.hasConditionRespect(personnage)){
                return false;
            };
        }
        return true;
    }

    public void ajouteCondition(GestionCondition condition) {
        getConditions().add(condition);
    }

    public List<GestionCondition> getConditions() {
        return conditions;
    }

}
