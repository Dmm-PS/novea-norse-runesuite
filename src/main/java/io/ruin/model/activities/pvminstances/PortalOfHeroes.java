package io.ruin.model.activities.pvminstances;

import io.ruin.model.activities.wilderness.BossEvent;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;

public class PortalOfHeroes {

	private static void enter(Player player, GameObject object) {
			player.dialogue(new OptionsDialogue("Where do you want to go?",
					new Option("Wilderness Resource Area", PortalOfHeroes::teleportToResourceArea), new Option("Revenant Cave", PortalOfHeroes::teleportToResourceArea)));
	}

	private static void teleportToResourceArea(Player player) {
		player.getMovement().startTeleport(e -> {
			player.animate(714);
			player.graphics(111, 92, 0);
			player.publicSound(200);
			e.delay(3);
			player.getMovement().teleport(3187, 3937, 0);
		});
	}

	private static void teleportToRevs(Player player) {
		player.getMovement().startTeleport(e -> {
			player.animate(714);
			player.graphics(111, 92, 0);
			player.publicSound(200);
			e.delay(3);
			player.getMovement().teleport(3254, 10194, 0);
		});
	}

	static {
		ObjectAction.register("Portal of Heroes", "Enter", PortalOfHeroes::enter);
	}

}
