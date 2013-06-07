<%@ page import="com.trinary.Collectomundo.Game" %>

<g:hiddenField name="platform.id" value="${gameInstance ? gameInstance.platform.id : platform}" />

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="game.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${gameInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'rarity', 'error')}">
	<label for="rarity">
		<g:message code="game.rarity.label" default="Rarity" />
	</label>
	<g:field name="rarity" type="number" value="${gameInstance.rarity}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'releaseDate', 'error')}">
	<label for="releaseDate">
		<g:message code="game.releaseDate.label" default="Release Date" />
	</label>
	<g:datePicker name="releaseDate" precision="day"  value="${gameInstance?.releaseDate}"  />
</div>

