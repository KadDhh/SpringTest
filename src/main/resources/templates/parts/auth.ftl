<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
<#assign
user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
name = user.getUsername()
isAdmin = user.isAdmin()
isAuth = user.isUser()
>
<#else>
<#assign
isAuth = false
isAdmin = false
>
</#if>
