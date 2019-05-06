<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div class="mb-3"/>Страница авторизации
</div>
<@l.login "/login" false/>
</@c.page>