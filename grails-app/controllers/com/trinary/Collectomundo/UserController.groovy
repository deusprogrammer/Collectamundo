package com.trinary.Collectomundo

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	SpringSecurityService springSecurityService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        User userInstance = new User(params)
		Role role = Role.findByAuthority("ROLE_USER")
		
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }
		
		if (role && userInstance) {
			UserRole.create userInstance, role, true
		}

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
		
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }
		
		if (userInstance != springSecurityService.getCurrentUser() && !springSecurityService.getCurrentUser().getAuthorities().authority.contains("ROLE_ROOT")) {
			flash.message = "You lack the privileges to alter this user."
			redirect(action: "list")
			return
		}

        [userInstance: userInstance]
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
				
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }
		
		if (userInstance != springSecurityService.getCurrentUser() && !userInstance.getAuthorities().contains("ROLE_ROOT")) {
			flash.message = "You lack the privileges to alter this user."
			redirect(action: "list")
			return
		}

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }
		
		if (userInstance != springSecurityService.getCurrentUser() && !userInstance.getAuthorities().contains("ROLE_ROOT")) {
			flash.message = "You lack the privileges to alter this user."
			redirect(action: "list")
			return
		}

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
	
	@Secured(['ROLE_ROOT', 'ROLE_ADMIN'])
	def giveAdmin(Long id) {
		User user = User.get(id)
		Role role = Role.findByAuthority("ROLE_ADMIN")
		
		if (role && user) {
			UserRole.create user, role, true
		} else {
			flash.message = "Unable to find user or root role"
		}
		
		redirect(action: "show", id: id)
	}
	
	@Secured(['ROLE_ROOT', 'ROLE_ADMIN'])
	def removeAdmin(Long id) {
		User user = User.get(id)
		Role role = Role.findByAuthority("ROLE_ADMIN")
		
		if (role && user) {
			UserRole.remove user, role, true
		} else {
			flash.message = "Unable to find user or root role"
		}
		
		redirect(action: "show", id: id)
	}
	
	@Secured(['ROLE_ROOT'])
	def giveRoot(Long id) {
		User user = User.get(id)
		Role role = Role.findByAuthority("ROLE_ROOT")
		
		if (role && user) {
			UserRole.create user, role, true
		} else {
			flash.message = "Unable to find user or root role"
		}
		
		redirect(action: "show", id: id)
	}
	
	@Secured(['ROLE_ROOT'])
	def removeRoot(Long id) {
		User user = User.get(id)
		Role role = Role.findByAuthority("ROLE_ROOT")
		
		if (role && user) {
			UserRole.remove user, role, true
		} else {
			flash.message = "Unable to find user or root role"
		}
		
		redirect(action: "show", id: id)
	}
}
