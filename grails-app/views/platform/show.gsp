
<%@ page import="com.trinary.Collectomundo.Platform" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'platform.label', default: 'Console')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-platform" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-platform" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list platform">
			
				<g:if test="${platformInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="platform.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${platformInstance}" field="name"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${platformInstance?.company}">
				<li class="fieldcontain">
					<span id="company-label" class="property-label"><g:message code="platform.company.label" default="Company" /></span>
					
						<span class="property-value" aria-labelledby="company-label"><g:link controller="company" action="show" id="${platformInstance?.company?.id}">${platformInstance?.company?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${platformInstance?.releaseDate}">
				<li class="fieldcontain">
					<span id="releaseDate-label" class="property-label"><g:message code="platform.releaseDate.label" default="Release Date" /></span>
					
						<span class="property-value" aria-labelledby="releaseDate-label"><g:formatDate date="${platformInstance?.releaseDate}" /></span>
					
				</li>
				</g:if>
				
				<g:if test="${platformInstance?.endOfLife}">
				<li class="fieldcontain">
					<span id="endOfLife-label" class="property-label"><g:message code="platform.endOfLife.label" default="End Of Life" /></span>
					
						<span class="property-value" aria-labelledby="endOfLife-label"><g:formatDate date="${platformInstance?.endOfLife}" /></span>
					
				</li>
				</g:if>
		
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${platformInstance?.id}" />
					<g:link class="edit" action="edit" id="${platformInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="edit" controller="game" action="create" params="${['platform.id': platformInstance.id]}">Add Game</g:link>
					<g:link class="edit" controller="platform" action="editGameLibrary" id="${platformInstance.id}">Add Games (List)</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
