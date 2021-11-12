# SillyCore - Core Plugin for Minecraft Server Administrators

SillyCore is a plugin for Minecraft servers running Spigot/forks of spigot (i.e Paper, Purper, Tuinity). SillyCore provides everything you need to run your Minecraft server, including:

- Fully customizable plugin messages
- Fully custom punishment system, with GUIs
- A vast range of commands, for all your server needs. 

## Compatability

SillyCore has been tested on Minecraft 1.17 and 1.17.1.

## Contributing

If you would like to contribute, please follow the contributing guide. 

When contributing, check that you aren't:

- Contributing unnecessary features
- Contributing something that slows down or hurts the performance of the plugin.
- Literaly whitespace or unnecessary comments
- Indescriptive commit messages

This is a good commit message:

- Message: 'Optimize the /ban command by modifying the way players are stored.'

This is a bad commit message:
- Message: 'Change /ban'
- Message: 'Modify'
- Message: 'a'

## Reporting Bugs

Think there's a bug? Great! Report it in the issues page.

### Bug Reporting Guide 

Before anything, check:
- Is this bug worth reporting? 
- Has this bug already been reported?

### Bug Reporting Format

Follow this format when reporting bugs:

Title: Some commands are incorrectly sending messages, as well as some not using the lang.yml messages. 

### Bug

Plugin: 1.0-developer (NEVER say 'latest')
Minecraft Version: 1.17.1 (NEVER say 'latest')
Server: Paper (Server version also helpful)

### What should happen:

Commands should output their correct messages, by being grabbed from the lang.yml file.

### What happens:

Some of the messages either aren't formatted correctly, or simply not from the lang.yml.

### How to reproduce:

Use some of the commands in the plugin, (/invview and /tp are bugged iirc)


## Compiling

The plugin currently uses the OpenJDK ARM Java 17 to compile with maven. To compile, `cd` into the root folder of the plugin
and type: `mvn clean install`
If there are any errors please put them in issues.

## License
The plugin uses the MIT license and can be viewed [here.](https://git.sillysock.codes/Sillysock/SillyCore/src/branch/dev/test/temppunishment/LICENSE)
