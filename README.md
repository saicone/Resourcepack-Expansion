# ResourcePack Expansion

Adds placeholders to get resource pack related stuff.

## Placeholder

* `%resourcepack_loaded%` - Returns `true` if the player is using the resource pack, `false` otherwise.
* `%resourcepack_status%` - Returns the current [resource pack status](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/event/player/PlayerResourcePackStatusEvent.Status.html) from player.
* `%resourcepack_id%` - Returns the id of the resource pack.
* `%resourcepack_url%` - Returns the current resource pack url.
* `%resourcepack_hash%` - Returns the SHA-1 digest of the server resource pack.
* `%resourcepack_prompt%` - Returns the custom prompt message to be shown when the server resource pack is required.
* `%resourcepack_required%` - Returns `true` if the server resource pack is required, `false` otherwise.