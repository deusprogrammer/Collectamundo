package com.trinary.Collectomundo.ownerships

import com.trinary.Collectomundo.Console
import com.trinary.Collectomundo.user.User

class ConsoleOwnership {
	User user
	Console console
	
	Boolean tradeable = false
	Boolean saleable = false
	Float price = 0.00f

    static constraints = {
    }
}
