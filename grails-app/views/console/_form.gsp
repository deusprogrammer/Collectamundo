<%@ page import="com.trinary.Collectomundo.Console" %>

<g:hiddenField name="company.id" value="${consoleInstance ? consoleInstance.company.id : company}" />

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