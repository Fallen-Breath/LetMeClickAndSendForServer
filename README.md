## Let Me Click And Send for Server

[![MC Versions](https://cf.way2muchnoise.eu/versions/For%20MC_1107596_all.svg)](https://www.curseforge.com/minecraft/mc-mods/let-me-click-and-send-for-server)
[![CurseForge](https://cf.way2muchnoise.eu/full_1107596_downloads.svg)](https://legacy.curseforge.com/minecraft/mc-mods/let-me-click-and-send-for-server)
[![Modrinth](https://img.shields.io/modrinth/dt/HtZfkfG8?label=Modrinth%20Downloads)](https://modrinth.com/project/let-me-click-and-send-for-server)

A simple Minecraft server-side mod, that replaces all non-command `run_command` content with a custom command,
to bypass client-side restrictions since 1.19.1-rc1

If you want a more direct solution, here's a client-side only mod that does the same thing:
[LetMeClickAndSend](https://github.com/Fallen-Breath/LetMeClickAndSend)

| Mod                                                                                       | Side   | Advantage                                 | Disadvantage                                  |
|-------------------------------------------------------------------------------------------|--------|-------------------------------------------|-----------------------------------------------|
| [LetMeClickAndSend](https://github.com/Fallen-Breath/LetMeClickAndSend)                   | client | Exactly the same behavior as pre mc1.19.1 | Needs to be installed on all players' clients |        
| [LetMeClickAndSendForServer](https://github.com/Fallen-Breath/LetMeClickAndSendForServer) | server | No need to install on client              | Reduces maximum chat message length by 7      |     

### Example

Let's run the following command, and then click the shown text

```bash
# Minecraft [1.7, 1.21.5)
/tellraw @a {"text":"click me to send \"hi\"","clickEvent":{"action":"run_command","value":"hi"}}
```

In vanilla 1.19.1 ~ 1.21.4, after clicking, you are not able to say anything since `hi` is not a valid command (not starts with `/`)

With this mod, the server will automatically replace the `value` field in the click event above with `/lmcas hi`,
so the client can "send" the message without any issue after the click

On command received, the server will broadcast a message with the same format as a player message, simulating the player saying "hi" in chat

### MC 1.21.5+

Since MC 1.21.5, the `run_command` behavior has changed a lot

First is the change in command syntax, which has little impact:

```bash
# Minecraft [1.21.5, ~)
/tellraw @a {"text":"click me to send \"hi\"","click_event":{"action":"run_command","command":"hi"}}
```

Next is the change in behavior, which has a greater impact:

- The `command` value is always valid, regardless of whether it starts with a `/` or not
- The client will strip the `/` prefix from the `command` value and send the remaining string as a command

It's no longer possible to correctly distinguish between "a run_command for sending chat message" and "a run_command for sending command"

As a workaround, LetMeClickAndSendForServer for MC >= 1.21.5 will only replace certain `command` with the `/lmcas` command.
By default, only `command` value starting with `!!`, which is a commonly-used command prefix 
in [MCDReforged](https://github.com/MCDReforged/MCDReforged) plugin ecosystem, will be replaced

A config file located at `./config/letmeclickandsendforserver/config.json` is added for customizing the replacing behavior

```json
{
    "replacePattern": "!!.*"
}
```

The `replacePattern` should be a valid regex pattern. All `command` values that fully match the pattern will be replaced with the `/lmcas` command

To test with the default configuration, you can use:

```bash
/tellraw @a {"text":"click me to send \"!!MCDR\"","click_event":{"action":"run_command","command":"!!MCDR"}}
```

### Requirements

It's a server-side only mod. It requires 0 extra dependency
