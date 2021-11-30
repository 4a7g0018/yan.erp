function show_manager_member_center(){
    $.ajax({
        type: 'GET',
        url: '/rd/member/get_rd_users',
        async: true,
        success: function (data) {
            // let str = "";
            console.log("----------"+data)
            let json_data = JSON.parse(JSON.stringify(data));
            show_manager_table_thead();
            show_manger_table_body(json_data);
            add_back_btn();
        },
        error: function (e) {
            console.log(e)
        }
    });

}

function show_manager_table_thead(){
    let thead = '<th style="width: 10%;min-width: 100px">image</th>'
        +'<th style="width: 10%;min-width: 125px">id</th>'
        +'<th style="width: 20%;min-width: 225px">users name</th>'
        +'<th style="width: 30%;min-width: 300px">E-Mail</th>'
        +'<th style="width: 30%;min-width: 225px">phone</th>'
        // +'<th style="width: 20%;min-width: 200px">Enabled</th>'

    $('#table-thead').html(thead);
}

function show_manger_table_body(json_data){
    let str ='';
    console.log(json_data)
    for (let number in json_data) {
        let image = "data:image/jpg;base64," + json_data[number]["users"]["image"]
        console.log("id:"+json_data[number]["users"]["id"])
        str += '<tr onclick="show_cover(' + json_data[number]["users"]["id"] + ')">'
        str += '<td><img src="' + image + '" style="width: 80px;height:80px;margin-top: 2px" alt=""></td>'
            + '<td>' + json_data[number]["users"]["id"] + '</td>'
            + '<td>' + json_data[number]["users"]["userName"] + '</td>'
            + '<td>' + json_data[number]["users"]["email"] + '</td>'
            + '<td>' + json_data[number]["users"]["phone"] + '</td>'
            + '<td>'
            // + '<button onclick="show_cover(' + json_data[number]["users"]["id"] + ')" class="edit_button_user">edit</button>'
            // + '<button onclick="delete_user(' + json_data[number]["users"]["id"] + ')" class="delete_button_user">delete</button>'
            + '</td>'
        str += "</tr>"
    }
    $('#table-center').html(str);
}

function add_back_btn(){
    let close_btn = '<button type="button" class="cover_table_button_back" onclick="manager_clear_table()">back</button>'
    $('#close_btn').html(close_btn);
}

function show_cover(edit_user_id=null) {
    cover.classList.toggle('click');
    cover_table_userid.classList.toggle('click');
    cover_table_user_name.classList.toggle('click');
    cover_table_email.classList.toggle('click');
    console.log("close start")
    cover_table_phone.classList.toggle('click');
    console.log("close end")
    cover_table_button.classList.toggle('click');

    if (edit_user_id !== null) {
        console.log("edit_user_id:"+edit_user_id)
        $.ajax({
            type: 'GET',
            url: '/rd/member/edit/' + edit_user_id,
            async: true,
            success: function (data) {
                if (data != null) {
                    console.log(data);
                    var user_id = JSON.parse(JSON.stringify(data["id"]));
                    var user_name = JSON.parse(JSON.stringify(data["userName"]));
                    // var password = JSON.parse(JSON.stringify(data["password"]));
                    var email = JSON.parse(JSON.stringify(data["email"]));
                    var phone = JSON.parse(JSON.stringify(data["phone"]));
                    console.log(data)


                    document.getElementById("id").value = user_id;
                    document.getElementById("user_name").value = user_name;
                    document.getElementById("email").value = email;
                    document.getElementById("phone").value = phone;
                    // if (role_admin === "ADMIN") {
                    //     document.getElementById("role_admin").checked = true;
                    // }
                }
            }
        });

    } else {
        console.log("user id is null or not defined");
    }
}

function manager_clear_table() {
    document.getElementById("id").value = null;
    document.getElementById("user_name").value = null;
    document.getElementById("email").value = null;
    document.getElementById("phone").value = null;
    // document.getElementById("role_admin").value = "";
    close_cover()
}

function close_cover(){
    show_cover();
}