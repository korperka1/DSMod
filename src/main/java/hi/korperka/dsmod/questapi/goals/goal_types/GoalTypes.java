package hi.korperka.dsmod.questapi.goals.goal_types;

import dev.zanckor.api.enuminterface.enumquest.IEnumQuestGoal;
import dev.zanckor.api.filemanager.quest.abstracquest.AbstractGoal;
import hi.korperka.dsmod.questapi.goals.goals.NothingGoal;

public enum GoalTypes implements IEnumQuestGoal {
    NOTHING(new NothingGoal());

    AbstractGoal goal;
    GoalTypes(AbstractGoal abstractGoal) {
        this.goal = abstractGoal;
        registerEnumGoal(this.getClass());
    }

    @Override
    public AbstractGoal getQuest() {
        return goal;
    }
}
