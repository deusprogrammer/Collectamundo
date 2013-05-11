
<%@ page import="com.trinary.Collectomundo.Accessory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'accessory.label', default: 'Accessory')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-accessory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-accessory" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list accessory">
			
				<g:if test="${accessoryInstance?.console}">
				<li class="fieldcontain">
					<span id="console-label" class="property-label"><g:message code="accessory.console.label" default="Console" /></span>
					
						<span class="property-value" aria-labelledby="console-label"><g:link controller="console" action="show" id="${accessoryInstance?.console?.id}">${accessoryInstance?.console?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${accessoryInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="accessory.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${accessoryInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accessoryInstance?.rarity}">
				<li class="fieldcontain">
					<span id="rarity-label" class="property-label"><g:message code="accessory.rarity.label" default="Rarity" /></span>
					
						<span class="property-value" aria-labelledby="rarity-label"><g:fieldValue bean="${accessoryInstance}" field="rarity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accessoryInstance?.releaseDate}">
				<li class="fieldcontain">
					<span id="releaseDate-label" class="property-label"><g:message code="accessory.releaseDate.label" default="Release Date" /></span>
					
						<span class="property-value" aria-labelledby="releaseDate-label"><g:formatDate date="${accessoryInstance?.releaseDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${accessoryInstance?.id}" />
					<g:link class="edit" action="edit" id="${accessoryInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
