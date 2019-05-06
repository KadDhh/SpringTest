<#include "auth.ftl">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">KadDhh</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Сообщения</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/gamePage">Кликер</a>
            </li>

            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">Список пользователей</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/messageControl">Удаление сообщений</a>
            </li>
            </#if>
        </ul>

        <#if isAuth>
        <div class="navbar-text mr-3" >${name}</div>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Выход</button>
        </form>
        <#else>
        <form action="/login" method="get">
        <button class="btn btn-primary" type="submit">Вход</button>
        </form>
        </#if>

    </div>
</nav>