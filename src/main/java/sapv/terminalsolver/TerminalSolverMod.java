package sapv.terminalsolver;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminalSolverMod implements ModInitializer {
	public static final String MOD_ID = "terminal-solver";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// global end tick -> check terminal -> get solution
		// 틱마다? 랜더틱마다?
		// 터미널 랜더링
		// 터미널 창 이름 보고 매치 시키는거
		// 솔루션의 공통된 그런거 Solution{ slot, button,  }
		// post screen render mixin

		/*
		* 틱 베이스
		* 간단함, 클릭한거 보정 해야됨
		* */

		// 패킷 베이스
		// 복잡함, 클릭한거 보정 필요 없음

		TerminalSolver.init();

		test();
	}

	private static void test() {
	}
}