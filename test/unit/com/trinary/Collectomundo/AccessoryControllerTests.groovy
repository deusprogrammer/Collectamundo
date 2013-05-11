package com.trinary.Collectomundo



import org.junit.*
import grails.test.mixin.*

@TestFor(AccessoryController)
@Mock(Accessory)
class AccessoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/accessory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.accessoryInstanceList.size() == 0
        assert model.accessoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.accessoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.accessoryInstance != null
        assert view == '/accessory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/accessory/show/1'
        assert controller.flash.message != null
        assert Accessory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/accessory/list'

        populateValidParams(params)
        def accessory = new Accessory(params)

        assert accessory.save() != null

        params.id = accessory.id

        def model = controller.show()

        assert model.accessoryInstance == accessory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/accessory/list'

        populateValidParams(params)
        def accessory = new Accessory(params)

        assert accessory.save() != null

        params.id = accessory.id

        def model = controller.edit()

        assert model.accessoryInstance == accessory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/accessory/list'

        response.reset()

        populateValidParams(params)
        def accessory = new Accessory(params)

        assert accessory.save() != null

        // test invalid parameters in update
        params.id = accessory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/accessory/edit"
        assert model.accessoryInstance != null

        accessory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/accessory/show/$accessory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        accessory.clearErrors()

        populateValidParams(params)
        params.id = accessory.id
        params.version = -1
        controller.update()

        assert view == "/accessory/edit"
        assert model.accessoryInstance != null
        assert model.accessoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/accessory/list'

        response.reset()

        populateValidParams(params)
        def accessory = new Accessory(params)

        assert accessory.save() != null
        assert Accessory.count() == 1

        params.id = accessory.id

        controller.delete()

        assert Accessory.count() == 0
        assert Accessory.get(accessory.id) == null
        assert response.redirectedUrl == '/accessory/list'
    }
}
