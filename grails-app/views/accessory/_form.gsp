<%@ page import="com.trinary.Collectomundo.Accessory" %>



<div class="fieldcontain ${hasErrors(bean: accessoryInstance, field: 'console', 'error')} required">
	<label for="console">
		<g:message code="accessory.console.label" default="Console" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="console" name="console.id" from="${com.trinary.Collectomundo.Console.list()}" optionKey="id" required="" value="${accessoryInstance?.console?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accessoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="accessory.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${accessoryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accessoryInstance, field: 'rarity', 'error')} required">
	<label for="rarity">
		<g:message code="accessory.rarity.label" default="Rarity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="rarity" type="number" value="${accessoryInstance.rarity}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: accessoryInstance, field: 'releaseDate', 'error')} required">
	<label for="releaseDate">
		<g:message code="accessory.releaseDate.label" default="Release Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="releaseDate" precision="day"  value="${accessoryInstance?.releaseDate}"  />
</div>

