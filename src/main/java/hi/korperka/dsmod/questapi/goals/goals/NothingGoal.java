package hi.korperka.dsmod.questapi.goals.goals;

import com.google.gson.Gson;
import dev.zanckor.api.filemanager.quest.abstracquest.AbstractGoal;
import dev.zanckor.api.filemanager.quest.codec.user.UserGoal;
import dev.zanckor.api.filemanager.quest.codec.user.UserQuest;
import hi.korperka.dsmod.questapi.goals.goal_types.GoalTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.io.File;
import java.io.IOException;

public class NothingGoal extends AbstractGoal {
    public void handler(ServerPlayer player, Entity entity, Gson gson, File file, UserQuest userQuest, int indexGoal, Enum questType) throws IOException {
        super.handler(player, entity, gson, file, userQuest, indexGoal, questType);
    }

    @Override
    public void enhancedCompleteQuest(ServerPlayer player, File file, UserGoal userGoal) {
    }

    @Override
    public void updateData(ServerPlayer player, File file) {
    }

    @Override
    public GoalTypes getGoalType() {
        return GoalTypes.NOTHING;
    }
}
