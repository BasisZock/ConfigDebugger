package BasisZock.github.io.configDebugger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class DebugConfigCommand implements CommandExecutor {

    private void scanDirectory(File dir, List<File> yamlFiles) {
        if (dir == null || !dir.exists()) {
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectory(file, yamlFiles);
            } else if (file.isFile() && file.getName().toLowerCase().endsWith(".yml")) {
                yamlFiles.add(file);
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        File pluginsFolder = new File("plugins");
        if (!pluginsFolder.exists() || !pluginsFolder.isDirectory()) {
            sender.sendMessage(ChatColor.RED + "Plugins folder not found.");
            return true;
        }

        List<File> yamlFiles = new ArrayList<>();
        scanDirectory(pluginsFolder, yamlFiles);

        if (yamlFiles.isEmpty()) {
            sender.sendMessage(ChatColor.YELLOW + "No YAML configuration files found in the plugins folder.");
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + "Scanning " + yamlFiles.size() + " YAML files for syntax errors...");

        Yaml yaml = new Yaml();

        int errorCount = 0;
        for (File file : yamlFiles) {
            try (FileInputStream fis = new FileInputStream(file)) {
                yaml.load(fis);
                sender.sendMessage(ChatColor.GRAY + "OK: " + file.getPath());
            } catch (Exception e) {
                errorCount++;
                sender.sendMessage(ChatColor.RED + "Error in " + file.getPath() + ": " + e.getMessage());
            }
        }

        if (errorCount > 0) {
            sender.sendMessage(ChatColor.RED + "Finished scanning. " + errorCount + " file(s) contained errors.");
        } else {
            sender.sendMessage(ChatColor.GREEN + "Finished scanning. All YAML files are valid.");
        }
        return true;
    }
}
