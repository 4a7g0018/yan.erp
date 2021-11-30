function show_user_member_center(){
    $.ajax({
        type: 'GET',
        url: '/account/member/get_account_users',
        async: true,
        success: function (data) {
            // let str = "";
            console.log("----------"+data)
            let json_data = JSON.parse(JSON.stringify(data));
            show_user_table_thead();
            show_user_table_body(json_data);
            add_close_btn();
        },
        error: function (e) {
            console.log(e)
        }
    });

}

function show_user_table_thead(){
    let thead = '<th style="width: 10%;min-width: 100px">image</th>'
        +'<th style="width: 10%;min-width: 175px">id</th>'
        +'<th style="width: 25%;min-width: 275px">users name</th>'
        +'<th style="width: 30%;min-width: 300px">E-Mail</th>'

    $('#table-thead').html(thead);
}

function show_user_table_body(json_data){
    let str ='';
    console.log(json_data)
    for (let number in json_data) {
        let image = "data:image/jpg;base64," + json_data[number]["users"]["image"]

        str += '<tr onclick="show_user_member_cover(' + json_data[number]["users"]["id"] + ')">'
        str += '<td><img src="' + image + '" style="width: 80px;height:80px;margin-top: 2px" alt=""></td>'
            + '<td>' + json_data[number]["users"]["id"] + '</td>'
            + '<td>' + json_data[number]["users"]["userName"] + '</td>'
            + '<td>' + json_data[number]["users"]["email"] + '</td>'
        str += "</tr>"
    }
    $('#table-center').html(str);
}

function add_close_btn(){
    let close_btn = '<button type="button" class="cover_table_button_back" onclick="user_member_clear_table()">back</button>'
    $('#close_btn').html(close_btn);
}

function show_user_member_cover(edit_user_id=null) {
    cover.classList.toggle('click');
    cover_table_userid.classList.toggle('click');
    cover_table_user_name.classList.toggle('click');
    cover_table_email.classList.toggle('click');
    // cover_table_update_image.classList.toggle('click');
    // cover_table_phone.classList.toggle('click');
    cover_table_button.classList.toggle('click');

    if (edit_user_id !== null) {
        console.log("edit_user_id:"+edit_user_id)
        $.ajax({
            type: 'GET',
            url: '/account/member/edit/' + edit_user_id,
            async: true,
            success: function (data) {
                if (data != null) {
                    console.log(data);
                    var user_id = JSON.parse(JSON.stringify(data["id"]));
                    var user_name = JSON.parse(JSON.stringify(data["userName"]));
                    // var password = JSON.parse(JSON.stringify(data["password"]));
                    var email = JSON.parse(JSON.stringify(data["email"]));
                    console.log(data)


                    document.getElementById("id").value = user_id;
                    document.getElementById("user_name").value = user_name;
                    document.getElementById("email").value = email;
                }
            }
        });

    } else {
        console.log("user id is null or not defined");
    }
}

function user_member_clear_table() {
    document.getElementById("id").value = null;
    document.getElementById("user_name").value = null;
    document.getElementById("email").value = null;
    // document.getElementById("role_admin").value = "";
    close_user_member_cover()
}

function close_user_member_cover(){
    show_user_member_cover();
}