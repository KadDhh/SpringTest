<#import "parts/common.ftl" as c>

<@c.page>
<div> Удаление сообщения </div>
<form method="post" action="/messageControl">

<input type="text" name="messageId" placeholder="ID">

    <input type="hidden" name="_csrf" value="${_csrf.token}" />

<button type="submit" name="messageDelete">Удалить</button>
</form>
${message?ifExists}
</@c.page>
