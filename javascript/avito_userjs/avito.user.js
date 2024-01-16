// ==UserScript==
// @name thinkpad
// @namespace avito
// @include https://avito.ru/*
// @require https://code.jquery.com/jquery-3.6.0.min.js
// @version 0.1
// grant none
// ==/UserScript==
//______________________________________________________________________________________________________________________
// CONFIG
var hucksters = [
    "934c7dfe29e806b93b3b8a630b4e2a92", // Скупка
    "d30e6bc93babe8eaa86ae1eb76e730e0", // Комиссионка Бутово
    "459f377e80cf0c7198ba54578c32a415", // ShopOnTheSofa
    "f88da0bf831961b0db0052fefd7eee45", // Олег (reason too much bulletins)
    "265db35a626e61d7a05fde4b70e85f84", // Ноутбук с гарантией
    "864ee4e704f62bcefeb26287e42a4d94", // Корпоративная техника
    "aa4e6f07dae278b90111d97bda1a7468", // Ноутбуки оптом черная пятница
    "9daa4bf083d2f4201ac69ac73eb58350", // Арена
    "dbfa87953546a224c88f7b6307a99a72", // WhaleTop цифровой магазин
    "1daf4a15cf5b65cf05e77540f4f076c0", // Запчасти установка бесплатно
    

]
//______________________________________________________________________________________________________________________
function hide_vip_bulletin(){
    // div class="items-vip-KXPvy"
    // div class="items-vip-"
    let vip_bulletins = $("div.items-vip-KXPvy");
    $.each(vip_bulletins, function(){
        $(this).hide();
    })
}

function filter_products(){
    let products = $("div[data-marker='item']");
    $.each(products, function(){
        let seller_node = $("a[data-marker='item-link']", this);
        if (!seller_node){return null};
        let seller_uri = seller_node.attr("href");
        if (!seller_uri){
            // Company
            $(this).css("background-color", "red");
            return null
        };
        let seller_title = seller_node.attr("title");
        let seller_id = seller_uri.split("/")[2];
        if (hucksters.includes(seller_id)){
            $(this).css("background-color", "red");
        };
    })
}

$(document).ready(function(){
    hide_vip_bulletin();
    filter_products();
})