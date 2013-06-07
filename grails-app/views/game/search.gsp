
<%@ page import="com.trinary.Collectomundo.Game" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title>Search Results</title>
	</head>
	<body>
		<a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-game" class="content scaffold-list" role="main">
			<h1>Search Results</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form action="addAllToCollection">
				<table>
					<thead>
						<tr>
						
							<sec:ifLoggedIn><th>Own</th></sec:ifLoggedIn>
							
							<sec:ifLoggedIn><th>Want</th></sec:ifLoggedIn>
							
							<g:sortableColumn property="platform" title= "Platform" />
						
							<g:sortableColumn property="name" title="${message(code: 'game.name.label', default: 'Name')}" />
						
							<g:sortableColumn property="rarity" title="${message(code: 'game.rarity.label', default: 'Rarity')}" />
						
							<g:sortableColumn property="releaseDate" title="${message(code: 'game.releaseDate.label', default: 'Release Date')}" />
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${gameInstanceList}" status="i" var="gameInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<sec:ifLoggedIn><td><g:checkBox name="own" value="${gameInstance.id}" checked="${gameInstance.id in owned}"/></td></sec:ifLoggedIn>
									
							<sec:ifLoggedIn><td><g:checkBox name="want" value="${gameInstance.id}" checked="${false}"/></td></sec:ifLoggedIn>
						
							<td>${fieldValue(bean: gameInstance, field: "platform")}</td>
						
							<td><g:link action="show" id="${gameInstance.id}">${fieldValue(bean: gameInstance, field: "name")}</g:link></td>
						
							<td>${fieldValue(bean: gameInstance, field: "rarity")}</td>
						
							<td><g:formatDate date="${gameInstance.releaseDate}" /></td>
						
						</tr>
					</g:each>
					</tbody>
				</table>
				<fieldset class="buttons">
					<g:submitButton name="add" value="Add to Collection" />
				</fieldset>
			</g:form>
			<div class="pagination">
				<g:paginate controller="game" action="search" total="${gameInstanceTotal}" params="${params}" />
			</div>
		</div>
	</body>
</html>
