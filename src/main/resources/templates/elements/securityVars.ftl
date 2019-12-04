<#assign currContext = Session.SPRING_SECURITY_CONTEXT??
admin = false>

<#if currContext>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        username = user.getUsername()
        name = user.getName()
        surname = user.getSurname()
        country = user.getCountry()
        id = user.getId()>
	<#if username = "admin">
		<#assign admin = true>
	</#if>
</#if> 
 