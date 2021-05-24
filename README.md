# HttpPoster V2
### A simple plugin, that allows all your crazy dreams about connectivity
##### You can download the latest version from [Spigot](https://www.spigotmc.org/resources/httpposter-v2-0.90451/)
![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg) ![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)  

#### Configuration file:
```
version: '5.3' # DO NOT TOUCH THIS SETTING  

server-name: server # The name of the server, sent in every request  

events: # Available ingame events which generate data to carry to an endpoint  
	player: # Events regarding player interactions  
		join-leave: # Toggled when a player join or leave  
			enabled: false # Toggle on (true) off (false)  
			endpoint: http://www.example.com # The endpoint to hit 
			 
		chatted: # Toggled on chat message send by a player  
			enabled: false # Toggle on (true) off (false)  
			endpoint: http://www.example.com # The endpoint to hit  
			
		block-broken: # Toggled when a player breaks a block  
			enabled: false # Toggle on (true) off (false)  
			endpoint: http://www.example.com # The endpoint to hit  
			blacklist: # Ignored blocks  
			- GRASS_BLOCK  
			- COBBLESTONE  

		block-placed: # Toggled when a block is placed by a player  
			enabled: false # Toggle on (true) off (false)  
			endpoint: http://www.example.com # The endpoint to hit  
			blacklist: # Ignored blocks  
			- GRASS_BLOCK  
			- COBBLESTONE  

		mob-killed: # Toggled when a player kills a mob  
			enabled: false # Toggle on (true) off (false)  
			endpoint: http://www.example.com # The endpoint to hit  
			blacklist: # Ignored entities  
			- BAT  
			- ZOMBIE  
			
	server: # Events regarding the server  
		start-stop: # Toggled when the server boot or shutdown  
			enabled: false # Toggle on (true) off (false)  
			endpoint: http://www.example.com  
			
timers: # Timed data to send every x seconds to an endpoint  
	players: # Sends data about every online player in the server  
		enabled: false # Toggle on (true) off (false)  
		endpoint: http://www.example.com # The endpoint to hit  
		start-delay: 1 # Initial interval in seconds on server boot  
		interval-delay: 10 # Interval in seconds between every call  
		
messages: # Here you can use custom messages (& for colors)  
	no-permission: You don't have the permission to run this command.  
	not-found: Command not found. Use /http help for a list of the available commands.
```

### Further informations about the data object composition, can be found in the [spigot page](https://www.spigotmc.org/resources/httpposter-v2-0.90451/)