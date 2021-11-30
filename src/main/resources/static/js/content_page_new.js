let home_click = window.sessionStorage.getItem("home_click");
let accounting_click = window.sessionStorage.getItem("accounting_click");
let bar_toggle_click = window.sessionStorage.getItem("bar_toggle_click");

//new page
let work_list_click = window.sessionStorage.getItem("work_list_click");
let member_click = window.sessionStorage.getItem("member_click");
//new page--
let food_click = window.sessionStorage.getItem("food_click");

let home_a = document.querySelector('.home-a');
//new page
let work_list_a = document.querySelector('.work_list-a');
let member_a = document.querySelector('.member-a');
//new page--
let food_a = document.querySelector('.food-a');


let home = document.querySelector('.home');
let work_list = document.querySelector('.work_list');
let member = document.querySelector('.member');
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

//new page
if (work_list_click === 'click') {
    work_list.classList.toggle('hovered');
}

if (member_click === 'click') {
    member.classList.toggle('hovered');
}

if (food_click === 'click') {
    food.classList.toggle('hovered');
}
//new page--


home_a.onclick = function () {
    home_click = 'click';
    work_list_click = '';
    member_click = '';
    food_click = '';
}

//new page
work_list_a.onclick = function () {
    home_click = "";
    work_list_click = 'click';
    member_click = '';
    food_click = '';
}

member_a.onclick = function () {
    home_click = "";
    work_list_click = '';
    member_click = 'click';
    food_click = '';
}
//new page--

food_a.onclick = function () {
    home_click = '';
    work_list_click = '';
    member_click = '';
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
    window.sessionStorage.setItem("bar_toggle_click", bar_toggle_click);

    //new page
    window.sessionStorage.setItem("work_list_click", work_list_click);
    window.sessionStorage.setItem("member_click", member_click);
    window.sessionStorage.setItem("food_click", food_click);
    //new page--
}