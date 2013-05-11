
<%@ page import="com.trinary.Collectomundo.Game" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-game" class="content scaffold-list" role="main">
			<h1>${console} Games</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'game.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="rarity" title="${message(code: 'game.rarity.label', default: 'Rarity')}" />
					
						<g:sortableColumn property="releaseDate" title="${message(code: 'game.releaseDate.label', default: 'Release Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameInstanceList}" status="i" var="gameInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gameInstance.id}">${fieldValue(bean: gameInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: gameInstance, field: "rarity")}</td>
					
						<td><g:formatDate date="${gameInstance.releaseDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate controller="game" action="listByConsole" id="${console}" total="${gameInstanceTotal}" />
			</div>
		</div>
	</body>
</html>