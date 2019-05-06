<#import "parts/common.ftl" as c>
<#include "parts/auth.ftl">

<@c.page>
<div class="ml-1">Поиск по сообщению</div>
<form method="get" action="/main">
    <input type="text" name="filter" value="${filter?ifExists}">
    <button type="submit">Найти</button>
</form>

    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="text" placeholder="Введите сообщение" />
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <!-- Значение на контроллер передают name, там param -->
                <button type="submit" name="auth" class="btn btn-secondary">Опубликовать сообщение</button>

                <button type="submit" name="notAuth" class="btn btn-secondary">Опубликовать анонимно(фото загружено не будет)</button>
        </form>
            </form>

    </div>



<div class="card-columns">
    <#list messages as message>
    <div class="card my-3">
        <#if message.filename??>
        <img src="/img/${message.filename}" class="card-img-top">
    </#if>
    <div class="m-2">
        <span>${message.text}</span>

    </div>
    <!-- Показ id for admin -->
    <#if isAdmin>
    <div>
    <span>ID сообщения ${message.id}</span>
    </div>
    </#if>

    <div class="card-footer text-muted">
        ${message.authorName}
    </div>
    </div>
    <#else>
    Сообщений не найдено
</#list>
</div>
</@c.page>