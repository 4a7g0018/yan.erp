let home_click = window.sessionStorage.getItem("home_click");
let accounting_click = window.sessionStorage.getItem("accounting_click");
let bar_toggle_click = window.sessionStorage.getItem("bar_toggle_click");

//new page
let new_page_1_click = window.sessionStorage.getItem("new_page_1_click");
let new_page_2_click = window.sessionStorage.getItem("new_page_2_click");
//new page--
let food_click = window.sessionStorage.getItem("food_click");

let home_a = document.querySelector('.home-a');
let accounting_a = document.querySelector('.accounting-a');
//new page
let new_page_1_a = document.querySelector('.new-page-1-a');
let new_page_2_a = document.querySelector('.new-page-2-a');
//new page--
let food_a = document.querySelector('.food-a');


let home = document.querySelector('.home');
let accounting = document.querySelector('.accounting');
let new_page_1 = document.querySelector('.new-page-1');
let new_page_2 = document.querySelector('.new-page-2');
let food = document.querySelector('.food');

let bar_toggle = document.querySelector('.bar-toggle');
let navigation = document.querySelector('.navigation');
let main = document.querySelector('.main');

if (bar_toggle_click === 'click') {
    navigation.classList.toggle('active');
    main.classList.toggle('active');
}

if (home_click === "click") {
    home.classList.toggle('hovered');
}

if (accounting_click === "click") {
    accounting.classList.toggle('hovered');
}
//new page
if (new_page_1_click === 'click') {
    new_page_1.classList.toggle('hovered');
}

if (new_page_2_click === 'click') {
    new_page_2.classList.toggle('hovered');
}

if (food_click === 'click') {
    food.classList.toggle('hovered');
}
//new page--


home_a.onclick = function () {
    home_click = 'click';
    accounting_click = '';
    new_page_1_click = '';
    new_page_2_click = '';
    food_click = '';
}

accounting_a.onclick = function () {
    home_click = '';
    accounting_click = 'click';
    new_page_1_click = '';
    new_page_2_click = '';
    food_click = '';
}

//new page
new_page_1_a.onclick = function () {
    home_click = "";
    accounting_click = "";
    new_page_1_click = 'click';
    new_page_2_click = '';
    food_click = '';
}

new_page_2_a.onclick = function () {
    home_click = "";
    accounting_click = "";
    new_page_1_click = '';
    new_page_2_click = 'click';
    food_click = '';
}
//new page--

food_a.onclick = function () {
    home_click = '';
    accounting_click = '';
    new_page_1_click = '';
    new_page_2_click = '';
    food_click = 'click';
}

bar_toggle.onclick = function () {
    if (bar_toggle_click !== 'click') {
        bar_toggle_click = 'click';
    } else {
        bar_toggle_click = '';
    }

    navigation.classList.toggle('active');
    main.classList.toggle('active');
}

// pass value to other page
function pass_value() {
    window.sessionStorage.setItem("home_click", home_click);
    window.sessionStorage.setItem("accounting_click", accounting_click);
    window.sessionStorage.setItem("bar_toggle_click", bar_toggle_click);

    //new page
    window.sessionStorage.setItem("new_page_1_click", new_page_1_click);
    window.sessionStorage.setItem("new_page_2_click", new_page_2_click);
    window.sessionStorage.setItem("food_click", food_click);
    //new page--
}