package BasisZock.github.io.configDebugger;

import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigDebugger extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("debugconfig").setExecutor(new DebugConfigCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
