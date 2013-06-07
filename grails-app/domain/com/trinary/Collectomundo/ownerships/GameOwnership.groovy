package com.trinary.Collectomundo.ownerships

import com.trinary.Collectomundo.Game
import com.trinary.Collectomundo.user.User

class GameOwnership {
	User owner
	Game game
	
	Boolean tradeable = false
	Boolean saleable = false
	Float price = 0.00f

    static constraints = {
    }
}
