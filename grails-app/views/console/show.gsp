
<%@ page import="com.trinary.Collectomundo.Console" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'console.label', default: 'Console')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-console" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-console" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list console">
			
				<g:if test="${consoleInstance?.accessories}">
				<li class="fieldcontain">
					<span id="accessories-label" class="property-label"><g:message code="console.accessories.label" default="Accessories" /></span>
					
						<g:each in="${consoleInstance.accessories}" var="a">
						<span class="property-value" aria-labelledby="accessories-label"><g:link controller="accessory" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${consoleInstance?.company}">
				<li class="fieldcontain">
					<span id="company-label" class="property-label"><g:message code="console.company.label" default="Company" /></span>
					
						<span class="property-value" aria-labelledby="company-label"><g:link controller="company" action="show" id="${consoleInstance?.company?.id}">${consoleInstance?.company?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${consoleInstance?.endOfLife}">
				<li class="fieldcontain">
					<span id="endOfLife-label" class="property-label"><g:message code="console.endOfLife.label" default="End Of Life" /></span>
					
						<span class="property-value" aria-labelledby="endOfLife-label"><g:formatDate date="${consoleInstance?.endOfLife}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${consoleInstance?.games}">
				<li class="fieldcontain">
					<span id="games-label" class="property-label"><g:message code="console.games.label" default="Games" /></span>
					
						<g:each in="${consoleInstance.games}" var="g">
						<span class="property-value" aria-labelledby="games-label"><g:link controller="game" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${consoleInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="console.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${consoleInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${consoleInstance?.releaseDate}">
				<li class="fieldcontain">
					<span id="releaseDate-label" class="property-label"><g:message code="console.releaseDate.label" default="Release Date" /></span>
					
						<span class="property-value" aria-labelledby="releaseDate-label"><g:formatDate date="${consoleInstance?.releaseDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${consoleInstance?.id}" />
					<g:link class="edit" action="edit" id="${consoleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="edit" controller="game" action="create" params="${['console.id': consoleInstance.id]}">Add Game</g:link>
					<g:link class="edit" controller="console" action="editGameLibrary" id="${consoleInstance.id}">Add Games (List)</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
