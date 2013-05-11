<%@ page import="com.trinary.Collectomundo.Company" %>



<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'consoles', 'error')} ">
	<label for="consoles">
		<g:message code="company.consoles.label" default="Consoles" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${companyInstance?.consoles?}" var="c">
    <li><g:link controller="console" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="console" action="create" params="['company.id': companyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'console.label', default: 'Console')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: companyInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="company.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${companyInstance?.name}"/>
</div>

