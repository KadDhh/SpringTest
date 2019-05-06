<#import "parts/common.ftl" as c>

<@c.page>
<h4> Добро пожаловать в игру! </h4>
<h5> Одну игру на всех </h5>

<div> Очки = ${pointsShow?ifExists}</div>

<div> Здание1 = ${buildingOne?ifExists}</div>

<form method="post" action="/gamePage">
    <div class="mt-3"></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
<button type="submit" name="buttonClick" class="btn btn-secondary">получить инкам</button>
    <div class="mt-3"></div>
<button type="submit" name="buyBuildOne" class="btn btn-secondary">Купить здание1. Цена = ${buildingOnePrice?ifExists}</button>
</form>
${message?ifExists}
</@c.page>
