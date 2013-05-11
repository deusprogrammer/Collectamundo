<%@ page import="com.trinary.Collectomundo.Game" %>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'console', 'error')} required">
	<label for="console">
		<g:message code="game.console.label" default="Console" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="console" name="console.id" from="${com.trinary.Collectomundo.Console.list()}" optionKey="id" required="" value="${gameInstance?.console?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="game.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${gameInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'rarity', 'error')} required">
	<label for="rarity">
		<g:message code="game.rarity.label" default="Rarity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="rarity" type="number" value="${gameInstance.rarity}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'releaseDate', 'error')} required">
	<label for="releaseDate">
		<g:message code="game.releaseDate.label" default="Release Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="releaseDate" precision="day"  value="${gameInstance?.releaseDate}"  />
</div>

