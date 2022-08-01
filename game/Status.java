package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    FERTILE, // use this status to tell the current ground is fertile
    EFFECT_SUPER_MUSHROOM, // use this status when the player consumes super mushroom
    IMMUNITY, // use this status when the player consumes magical star, lasts for 10 turns
    DORMANT,
    BUYING, // use this status to tell which actor can buys item from toad.
    DRANK_POWER,
    KOOP,
    HAS_BOTTLE,
    FINAL_BOSS,
    WIN_GAME,
    FLYING,
    BURN, // lava ground will have this status to tell actor that the current ground can inflict a damage on actor.
    FIRE_ATTACK, // when the player consumes the fire flower , it will gain this status
    TELEPORT, // use this status to inform player that the ground can use to teleport player to other map.

}
