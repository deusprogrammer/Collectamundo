<%@ page import="com.trinary.Collectomundo.Console" %>

<div class="fieldcontain ${hasErrors(bean: consoleInstance, field: 'company', 'error')} required">
	<label for="company">
		<g:message code="console.company.label" default="Company" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="company" name="company.id" from="${com.trinary.Collectomundo.Company.list()}" optionKey="id" required="" value="${consoleInstance?.company?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: consoleInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="console.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${consoleInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: consoleInstance, field: 'abbreviation', 'error')} ">
	<label for="abbreviation">
		<g:message code="console.abbreviation.label" default="Abbreviation" />
		
	</label>
	<g:textField name="abbreviation" value="${consoleInstance?.abbreviation}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: consoleInstance, field: 'releaseDate', 'error')} required">
	<label for="releaseDate">
		<g:message code="console.releaseDate.label" default="Release Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="releaseDate" precision="day"  value="${consoleInstance?.releaseDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: consoleInstance, field: 'endOfLife', 'error')} required">
	<label for="endOfLife">
		<g:message code="console.endOfLife.label" default="End Of Life" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endOfLife" precision="day"  value="${consoleInstance?.endOfLife}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: consoleInstance, field: 'games', 'error')} ">
	<label for="games">
		<g:message code="console.games.label" default="Games" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${consoleInstance?.games?}" var="g">
    <li><g:link controller="game" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="game" action="create" params="['console.id': consoleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'game.label', default: 'Game')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: consoleInstance, field: 'accessories', 'error')} ">
	<label for="accessories">
		<g:message code="console.accessories.label" default="Accessories" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${consoleInstance?.accessories?}" var="a">
    <li><g:link controller="accessory" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="accessory" action="create" params="['console.id': consoleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'accessory.label', default: 'Accessory')])}</g:link>
</li>
</ul>

</div>