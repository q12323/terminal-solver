package sapv.terminalsolver;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminalSolverMod implements ClientModInitializer {
	public static final String MOD_ID = "terminal-solver";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		TerminalSolver.init();
	}
}