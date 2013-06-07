package com.trinary.Collectomundo

import org.springframework.dao.DataIntegrityViolationException

import com.trinary.Collectomundo.user.User;

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ROOT'])
class AccessoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [accessoryInstanceList: Accessory.list(params), accessoryInstanceTotal: Accessory.count()]
    }

    def create() {
        [accessoryInstance: new Accessory(params)]
    }

    def save() {
        def accessoryInstance = new Accessory(params)
        if (!accessoryInstance.save(flush: true)) {
            render(view: "create", model: [accessoryInstance: accessoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'accessory.label', default: 'Accessory'), accessoryInstance.id])
        redirect(action: "show", id: accessoryInstance.id)
    }

    def show(Long id) {
        def accessoryInstance = Accessory.get(id)
        if (!accessoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'accessory.label', default: 'Accessory'), id])
            redirect(action: "list")
            return
        }

        [accessoryInstance: accessoryInstance]
    }

    def edit(Long id) {
        def accessoryInstance = Accessory.get(id)
        if (!accessoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'accessory.label', default: 'Accessory'), id])
            redirect(action: "list")
            return
        }

        [accessoryInstance: accessoryInstance]
    }

    def update(Long id, Long version) {
        def accessoryInstance = Accessory.get(id)
        if (!accessoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'accessory.label', default: 'Accessory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (accessoryInstance.version > version) {
                accessoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'accessory.label', default: 'Accessory')] as Object[],
                          "Another user has updated this Accessory while you were editing")
                render(view: "edit", model: [accessoryInstance: accessoryInstance])
                return
            }
        }

        accessoryInstance.properties = params

        if (!accessoryInstance.save(flush: true)) {
            render(view: "edit", model: [accessoryInstance: accessoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'accessory.label', default: 'Accessory'), accessoryInstance.id])
        redirect(action: "show", id: accessoryInstance.id)
    }

    def delete(Long id) {
        def accessoryInstance = Accessory.get(id)
        if (!accessoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'accessory.label', default: 'Accessory'), id])
            redirect(action: "list")
            return
        }

        try {
            accessoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'accessory.label', default: 'Accessory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'accessory.label', default: 'Accessory'), id])
            redirect(action: "show", id: id)
        }
    }
	
	@Secured(['ROLE_USER', 'ROLE_ADMIN', 'ROLE_ROOT'])
	def addToCollection(Long id) {
		def user = User.get(params.user)
		def accessory = Accessory.get(id)
		
		if (user && accessory) {
			user.addToAccessories(accessory)
			user.save()
			redirect(action: "list")
		} else {
			flash.message = "You must supply both a user and a title in your parameters."
			redirect(action: "list")
		}
		return
	}
}
