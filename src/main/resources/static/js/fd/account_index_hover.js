let home_click = 'click';

///new page
let new_page_1_click = '';
let new_page_2_click = '';
//new page--
let food_click = '';
let bar_toggle_click = window.sessionStorage.getItem('bar_toggle_click');

let bar_toggle = document.querySelector('.bar-toggle');
let navigation = document.querySelector('.navigation');
let main = document.querySelector('.main');
let home = document.querySelector('.home');
let home_a = document.querySelector('.home-a');
//new page
let new_page_1_a = document.querySelector('.new-page-1-a');
let new_page_2_a = document.querySelector('.new-page-2-a');
//new page--
let food_a = document.querySelector('.food-a');
home.classList.toggle('hovered');


if (bar_toggle_click === "click") {
    navigation.classList.toggle('active');
    main.classList.toggle('active');
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

home_a.onclick = function () {
    home_click = 'click';
    new_page_1_click = '';
    new_page_2_click = '';
    food_click = '';
}


//new page
new_page_1_a.onclick = function () {
    home_click = "";
    new_page_1_click = 'click';
    new_page_2_click = '';
    food_click = '';
}

new_page_2_a.onclick = function () {
    home_click = "";
    new_page_1_click = '';
    new_page_2_click = 'click';
    food_click = '';
}
//new page--

food_a.onclick = function () {
    home_click = '';
    new_page_1_click = '';
    new_page_2_click = '';
    food_click = 'click';
}

//new page--

function pass_value() {
    window.sessionStorage.setItem("home_click", home_click);
    window.sessionStorage.setItem("bar_toggle_click", bar_toggle_click);

    //new page
    window.sessionStorage.setItem("new_page_1_click", new_page_1_click);
    window.sessionStorage.setItem("new_page_2_click", new_page_2_click);
    window.sessionStorage.setItem("food_click", food_click);
    //new page--
}