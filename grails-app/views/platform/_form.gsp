<%@ page import="com.trinary.Collectomundo.Platform" %>

<g:hiddenField name="company.id" value="${platformInstance ? platformInstance.company.id : company}" />

<div class="fieldcontain ${hasErrors(bean: platformInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="platform.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${platformInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: platformInstance, field: 'abbreviation', 'error')} ">
	<label for="abbreviation">
		<g:message code="platform.abbreviation.label" default="Abbreviation" />
		
	</label>
	<g:textField name="abbreviation" value="${platformInstance?.abbreviation}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: platformInstance, field: 'releaseDate', 'error')} required">
	<label for="releaseDate">
		<g:message code="platform.releaseDate.label" default="Release Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="releaseDate" precision="day"  value="${platformInstance?.releaseDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: platformInstance, field: 'endOfLife', 'error')} required">
	<label for="endOfLife">
		<g:message code="platform.endOfLife.label" default="End Of Life" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endOfLife" precision="day"  value="${platformInstance?.endOfLife}"  />
</div>