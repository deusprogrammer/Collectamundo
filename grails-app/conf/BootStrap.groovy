import com.trinary.Collectomundo.Role
import com.trinary.Collectomundo.user.User;
import com.trinary.Collectomundo.user.UserRole;

class BootStrap {

    def init = { servletContext ->
		def rootRole = Role.findByAuthority("ROLE_ROOT")
		def adminRole = Role.findByAuthority("ROLE_ADMIN")
		def userRole  = Role.findByAuthority("ROLE_USER")
		def rootUser = User.findByUsername("root")
		
		if (!rootRole) {
			rootRole = new Role(authority: "ROLE_ROOT").save()
		}
		
		if (!adminRole) {
			new Role(authority: "ROLE_ADMIN").save()
		}
		
		if (!userRole) {
			new Role(authority: "ROLE_USER").save()
		}
		
		if (!rootUser) {
			rootUser = new User(username: "root", password: "password", enabled: "true").save()
		}
		
		try {
			UserRole.create rootUser, rootRole, true
		} catch (Exception e) {
		}
    }
    def destroy = {
    }
}
