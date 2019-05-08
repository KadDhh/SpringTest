<#import "parts/common.ftl" as c>

<@c.page>
<h4> Добро пожаловать в игру! </h4>
<h5> Одну игру на всех </h5>

<div> Очки = ${pointsShow?ifExists}</div>

<div> Здание1 = ${buildingOne?ifExists}</div>

<div> Здание2 = ${buildingTwo?ifExists} </div>
<i>${info?ifExists}</i>
<#if lastUser??> Последние клики: ${lastUser} </#if>
<#if lastUser2??> и ${lastUser2} </#if>
<form method="post" action="/gamePage">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div class="mt-3"></div>
    <button type="submit" name="buttonClick" class="btn btn-success">Получить инкам = ${income?ifExists}</button>
    <div class="mt-3"></div>
    Здание 1. Доход 1. <button type="submit" name="buyBuildOne" class="btn btn-secondary">Купить 1. Цена = ${buildingOnePrice}</button>
    <button type="submit" name="buyBuildOne10" class="btn btn-secondary">Купить 10. Цена = ${buildingOnePrice * 10}</button>

    <div class="mt-3"></div>
    Здание 2. Доход 8. <button type="submit" name="buyBuildTwo" class="btn btn-secondary"> Купить 1. Цена = ${buildingTwoPrice}</button>
    <button type="submit" name="buyBuildTwo10" class="btn btn-secondary"> Купить 10. Цена = ${buildingTwoPrice * 10}</button>
</form>
</@c.page>
