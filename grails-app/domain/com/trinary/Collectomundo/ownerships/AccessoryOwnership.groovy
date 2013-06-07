package com.trinary.Collectomundo.ownerships

import com.trinary.Collectomundo.Accessory
import com.trinary.Collectomundo.user.User

class AccessoryOwnership {
	User user
	Accessory accessory
	
	Boolean tradeable = "false"
	Boolean saleable = "false"
	Float price = 0.00f

    static constraints = {
    }
}
