## Let Me Click And Send for Server

[![MC Versions](https://cf.way2muchnoise.eu/versions/For%20MC_1107596_all.svg)](https://www.curseforge.com/minecraft/mc-mods/let-me-click-and-send-for-server)
[![CurseForge](https://cf.way2muchnoise.eu/full_694888_downloads.svg)](https://legacy.curseforge.com/minecraft/mc-mods/let-me-click-and-send-for-server)
[![Modrinth](https://img.shields.io/modrinth/dt/pGbwwB5d?label=Modrinth%20Downloads)](https://modrinth.com/project/let-me-click-and-send-for-server)

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

```
/tellraw @a {"text":"click me to send \"hi\"","clickEvent":{"action":"run_command","value":"hi"}}
```

In vanilla 1.19.1+, after clicking, you are not able to say anything since `hi` is not a valid command (not starts with `/`)

With this mod, the server will automatically replace the `value` field in the click event above with `/lmcas hi`,
so the client can send the command without any issue after the click. 
On command received, the server will broadcast a message with the same format as a player message   

### Requirements

It's a server-side only mod. It requires 0 extra dependency
