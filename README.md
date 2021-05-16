# MinecraftToJMX
Bukkit/Bungee plugin to send Minecraft data to JMX

## Spigot
The following beams are available when using Spigot or one of its forks
### Bukkit Beam
Name: `me.fabrimat.minecrafttojmx:type=BukkitJMX`

| Name | Type | Description |
| ------------- | ------------- | ------------- |
| OnlinePlayers  | int | Amount of players online |
| SchedulerActiveWorkers  | int | Amount of active workers in the Bukkit scheduler. |
| SchedulerPendingTasks  | int | Amount of pending tasks in the Bukkit scheduler. |
| WorldChunkLoaded  | int | Total amount of chunks loaded in the server. |
| WorldEntities  | int | Total amount of entities loaded in the server. |
### Spark Beam
Requires [Spark](https://github.com/lucko/spark)

Name: `me.fabrimat.minecrafttojmx:type=SparkJMX`

| Name | Type |
| ------------- | ------------- |
| TpsLast5Sec  | double |
| TpsLast10Sec  | double |
| TpsLast1Min  | double |
| TpsLast5Min  | double |
| TpsLast15Min  | double |
| MSPTLast10Sec  | double |
| MSPTLast1Min  | double |
## Bungeecord
The following beams are available when using Bungeecord or one of its forks

### Bungee Beam
Name: `me.fabrimat.minecrafttojmx:type=BungeeJMX`

| Name | Type |
| ------------- | ------------- |
| ConnectedPlayers  | int |
