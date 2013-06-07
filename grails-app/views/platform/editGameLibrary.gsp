<%@ page import="com.trinary.Collectomundo.Platform" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'platform.label', default: 'Console')}" />
		<title>Add Games by List</title>
	</head>
	<body>
		<a href="#edit-platform" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="edit-platform" class="content scaffold-edit" role="main">
			<h1>Add Games by List</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${platformInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${platformInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${platformInstance?.id}" />
				<g:hiddenField name="version" value="${platformInstance?.version}" />
				<g:textArea name="list" />
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="uploadGameLibrary" value="${message(code: 'default.button.update.label', default: 'Add')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
