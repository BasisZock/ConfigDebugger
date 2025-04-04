# ConfigDebugger

ConfigDebugger is a lightweight Bukkit/Spigot plugin that scans all YAML configuration files in the `plugins` directory and checks for syntax errors.

## Features
- Recursively scans all `.yml` files in the `plugins` folder.
- Reports syntax errors in configuration files.
- Provides a summary of valid and invalid files.

## Installation
1. Download the latest release.
2. Place the `.jar` file into your server's `plugins` folder.
3. Restart the server.

## Usage
Run the following command in the server console or as a player with the necessary permissions:
/debugconfig
**Aliases:** `/dec`

## Permissions
configdebug.run allows the player to run the command.
Default: op

## License
This project is licensed under the **GPL-3.0-only**.
