<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Имя: </label>
        <div class="col-sm-4">
            <input type="text" name="username" class="form-control" placeholder="Введите имя" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Пароль: </label>
        <div class="col-sm-4">
            <input type="password" name="password" class="form-control" placeholder="Введите пароль. Или нет?" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn mr-5 btn-primary" type="submit"><#if isRegisterForm>Создать<#else>Вход</#if></button>
    <#if !isRegisterForm><a href="/registration">Зарегистрировать нового пользователя</a></#if>
</form>
</#macro>
