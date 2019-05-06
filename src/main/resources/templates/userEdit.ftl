<#import "parts/common.ftl" as c>

<@c.page>
Редактор прав пользователей

<form action="/user" method="post">
    <input type="text" name="username" value="${user.username}">
    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
    </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" name="Change">Сохранить изменения</button>
    <div><button class="mt-3" type="sumbit" name="Delete">Удалить пользователя</button>
</form>
</@c.page>