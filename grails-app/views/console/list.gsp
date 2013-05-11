
<%@ page import="com.trinary.Collectomundo.Console" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'console.label', default: 'Console')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-console" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-console" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'console.name.label', default: 'Name')}" />
						<th><g:message code="console.company.label" default="Company" /></th>
						<g:sortableColumn property="releaseDate" title="${message(code: 'console.releaseDate.label', default: 'Release Date')}" />
						<g:sortableColumn property="endOfLife" title="${message(code: 'console.endOfLife.label', default: 'End Of Life')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${consoleInstanceList}" status="i" var="consoleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
											
						<td><g:link action="show" id="${consoleInstance.id}">${fieldValue(bean: consoleInstance, field: "name")}</g:link></td>
						<td>${fieldValue(bean: consoleInstance, field: "company")}</td>					
						<td><g:formatDate date="${consoleInstance.releaseDate}" /></td>
						<td><g:formatDate date="${consoleInstance.endOfLife}" /></td>
						
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${consoleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
