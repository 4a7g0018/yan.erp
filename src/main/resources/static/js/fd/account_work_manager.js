function show_table_thead(){
    let table_thead='<tr>'
        +'<th style="width: 150px;max-width: 10%">name</th>'
        +'<th style="width: 100px;max-width: 12%">priority</th>'
        +'<th style="width: 150px;max-width: 10%">date</th>'
        +'<th style="width: 300px;max-width: 13%">participants</th>'
        +'<th style="width: 400px;max-width: 20%">illustrate</th>'
        +'<th style="width: 100px;max-width: 10%">announce</th>'
        +'<th style="width: 125px;max-width: 10%">finish</th>'
        +'<th style="width: 225px;max-width: 15%"></th>'
        +'</tr>'
    $("#table-thead").html(table_thead);
}

function show_table_body(work_list_json){
    let str='';
    if (work_list_json[0]['workName'] !== null) {
        for (let work_list_center in work_list_json) {
            console.log(work_list_json[work_list_center])

            str += '<tr>'
                + '<td>' + work_list_json[work_list_center]['workName'] + '</td>';

            str += '<td><div class="priority-wrapper">'
            switch (work_list_json[work_list_center]['priority']) {
                case 5:
                    str += '<div class="priority-red">' + work_list_json[work_list_center]['priority'] + '</div>';
                    break;
                case 4:
                    str += '<div class="priority-orange">' + work_list_json[work_list_center]['priority'] + '</div>';
                    break;
                case 3:
                    str += '<div class="priority-yellow">' + work_list_json[work_list_center]['priority'] + '</div>';
                    break;
                case 2:
                    str += '<div class="priority-green">' + work_list_json[work_list_center]['priority'] + '</div>';
                    break;
                case 1:
                    str += '<div class="priority-gray">' + work_list_json[work_list_center]['priority'] + '</div>';
                    break;
            }
            str += '</div></td>'

            let date = work_list_json[work_list_center]['date'].substr(0, 10)
            str += '<td>' + date + '</td>';
            str += '<td>';
            for (let participants_length in work_list_json[work_list_center]['participants']) {
                if (participants_length >= 1) {
                    str += ',' + work_list_json[work_list_center]['participants'][participants_length]
                } else {
                    str += work_list_json[work_list_center]['participants'][participants_length]
                }
            }
            str += '</td>';
            str += '<td>' + work_list_json[work_list_center]['illustrate'] + '</td>'
                + '<td>'
                + '<div class="checkbox-container">'
                + '<label>'
                + '<input id="announce_' + work_list_json[work_list_center]['id'] + '" type="checkbox" onclick="touch_announce(' + work_list_json[work_list_center]['id'] + ')"><span><i></i></span>'
                + '</label>'
                + '</div>'
                + '</td>'
                + '<td>'
                + '<div class="checkbox-container">'
                + '<label>'
                + '<input id="finish_' + work_list_json[work_list_center]['id'] + '" type="checkbox" onclick="touch_finish(' + work_list_json[work_list_center]['id'] + ')"><span><i></i></span>'
                + '</label>'
                + '</div>'
                + '</td>'
            str += '<td>'
                + '<button onclick="show_work(' + work_list_json[work_list_center]['id'] + ')" class="edit_button_work_list">edit</button>'
                + '<button onclick="delete_work_list(' + work_list_json[work_list_center]['id'] + ')" class="delete_button_work_list">delete</button>'
                + '</td>';
            str += '</tr>';


        }

    }

    $("#table-center").html(str);

    if (work_list_json[0]['id'] !== null) {
        for (let json_number in work_list_json) {
            console.log(work_list_json[json_number]['announce']);
            console.log(work_list_json[json_number]['finish']);
            console.log(work_list_json[json_number]['id']);
            let id = work_list_json[json_number]['id'];
            document.getElementById("announce_" + id).checked = work_list_json[json_number]['announce'];
            document.getElementById("finish_" + id).checked = work_list_json[json_number]['finish'];
        }
    }
}

function show_manager_center() {
    $.ajax({
        type: 'GET',
        url: '/fd/work_list/get_all_work',
        async: true,
        success: function (data) {

            let add_btn = '<button class="add_new_work_button" onclick="show_add_work_participants()">+ 新增工作</button>'
            $("#add_work").html(add_btn);
            work_list_json = JSON.parse(JSON.stringify(data));

            show_table_thead()
            document.getElementById("roleId").value = work_list_json[0]["roleId"]
            show_table_body(work_list_json)
            // if (work_list_json[0]['workName'] !== null) {
            //     for (let work_list_center in work_list_json) {
            //         console.log(work_list_json[work_list_center])
            //
            //
            //
            //         str += '<tr>'
            //             + '<td>' + work_list_json[work_list_center]['workName'] + '</td>';
            //
            //         str += '<td><div class="priority-wrapper">'
            //         switch (work_list_json[work_list_center]['priority']) {
            //             case 5:
            //                 str += '<div class="priority-red">' + work_list_json[work_list_center]['priority'] + '</div>';
            //                 break;
            //             case 4:
            //                 str += '<div class="priority-orange">' + work_list_json[work_list_center]['priority'] + '</div>';
            //                 break;
            //             case 3:
            //                 str += '<div class="priority-yellow">' + work_list_json[work_list_center]['priority'] + '</div>';
            //                 break;
            //             case 2:
            //                 str += '<div class="priority-green">' + work_list_json[work_list_center]['priority'] + '</div>';
            //                 break;
            //             case 1:
            //                 str += '<div class="priority-gray">' + work_list_json[work_list_center]['priority'] + '</div>';
            //                 break;
            //         }
            //         str += '</div></td>'
            //
            //         let date = work_list_json[work_list_center]['date'].substr(0, 10)
            //         str += '<td>' + date + '</td>';
            //         str += '<td>';
            //         for (let participants_length in work_list_json[work_list_center]['participants']) {
            //             if (participants_length >= 1) {
            //                 str += ',' + work_list_json[work_list_center]['participants'][participants_length]
            //             } else {
            //                 str += work_list_json[work_list_center]['participants'][participants_length]
            //             }
            //         }
            //         str += '</td>';
            //         str += '<td>' + work_list_json[work_list_center]['illustrate'] + '</td>'
            //             + '<td>'
            //             + '<div class="checkbox-container">'
            //             + '<label>'
            //             + '<input id="announce_' + work_list_json[work_list_center]['id'] + '" type="checkbox" onclick="touch_announce(' + work_list_json[work_list_center]['id'] + ')"><span><i></i></span>'
            //             + '</label>'
            //             + '</div>'
            //             + '</td>'
            //             + '<td>'
            //             + '<div class="checkbox-container">'
            //             + '<label>'
            //             + '<input id="finish_' + work_list_json[work_list_center]['id'] + '" type="checkbox" onclick="touch_finish(' + work_list_json[work_list_center]['id'] + ')"><span><i></i></span>'
            //             + '</label>'
            //             + '</div>'
            //             + '</td>'
            //         str += '<td>'
            //             + '<button onclick="show_work(' + work_list_json[work_list_center]['id'] + ')" class="edit_button_work_list">edit</button>'
            //             + '<button onclick="delete_work_list(' + work_list_json[work_list_center]['id'] + ')" class="delete_button_work_list">delete</button>'
            //             + '</td>';
            //         str += '</tr>';
            //
            //
            //     }
            //
            // }
            //
            // $("#table-center").html(str);
            //
            // if (work_list_json[0]['id'] !== null) {
            //     for (let json_number in work_list_json) {
            //         console.log(work_list_json[json_number]['announce']);
            //         console.log(work_list_json[json_number]['finish']);
            //         console.log(work_list_json[json_number]['id']);
            //         let id = work_list_json[json_number]['id'];
            //         document.getElementById("announce_" + id).checked = work_list_json[json_number]['announce'];
            //         document.getElementById("finish_" + id).checked = work_list_json[json_number]['finish'];
            //     }
            // }

        }
    });
}

function show_add_work() {
    cover.classList.toggle('click');
    cover_table_jub_title.classList.toggle('click');
    cover_table_priority.classList.toggle('click');
    cover_table_date.classList.toggle('click');
    cover_table_participants.classList.toggle('click');
    cover_table_illustrate.classList.toggle('click');
    cover_table_announce.classList.toggle('click');
    cover_table_finish.classList.toggle('click');
    cover_table_button.classList.toggle('click');
}

function show_work(work_list_id) {
    $.ajax({
        type: 'GET',
        url: '/fd/work_list/edit/' + work_list_id,
        sync: true,
        success: function (data) {
            let json = JSON.parse(JSON.stringify(data));
            // console.log(data);
            show_add_work_participants(json['participants']);
            document.getElementById('id').value = json['id'];
            document.getElementById('job_title').value = json['workName'];
            document.getElementById('priority').value = json['priority'];
            // let date = json['date'].replace(/\//g, '-');
            let date = json['date'].substr(0, 10)
            document.getElementById('date').value = date;
            document.getElementById('illustrate').value = json['illustrate'];
            document.getElementById('announce').checked = json['announce'];
            document.getElementById('finish').checked = json['finish']
            document.getElementById('roleId').checked = json['roleId']
        }
    });
    // window.location.reload();
}

function show_add_work_participants(section = []) {
    $.ajax({
        type: 'GET',
        url: '/fd/work_list/get_user',
        async: true,
        success: function (data) {
            let strs = "";
            console.log(data)
            for (let user_name in data) {
                console.log(data[user_name]);
                strs += '<div>'

                strs += '<input id="' + data[user_name] + '" type="checkbox" class="participants participants-quantity">'
                    + '<label for="' + data[user_name] + '">' + data[user_name] + '</label>';

                strs += '</div>'
            }
            console.log(strs)

            $('#participants_checkbox').html(strs);

            section.forEach(val => {
                document.getElementById(val).checked = true
            })
            show_add_work();
        }
    });

};

function clear_table() {
    document.getElementById("id").value = null;
    document.getElementById("job_title").value = null;
    document.getElementById("priority").value = null;
    document.getElementById("date").value = null;
    document.getElementsByClassName("participants").checked = false;
    document.getElementById("illustrate").value = null;
    document.getElementById("announce").checked = false;
    document.getElementById("finish").checked = false;
    show_add_work();
}

$('#myForm').submit((e) => {
    e.preventDefault();
})

function upload_work_list() {
    let formData = new FormData();

    //add participants list
    let participants = $('.participants-quantity')
    let participants_list = [];
    for (let i = 0; i < participants.length; i++) {
        console.log(participants[i].checked)
        console.log("111-" + participants[i].id)
        if (participants[i].checked) {

            // participants_list.push(i + 1)
            participants_list.push(participants[i].id)
        }
    }
    // console.log(participants_list)

    console.log("data")
    formData.append('workName', document.getElementById("job_title").value);
    console.log('workName:' + $('#job_title').val() + typeof $('#job_title').val())

    formData.append('priority', document.getElementById("priority").value);
    console.log('priority:' + $('#priority').val() + typeof $('#priority').val())

    formData.append('date', document.getElementById("date").value);
    console.log('date:' + $('#date').val() + typeof $('#date').val())

    formData.append('participants', participants_list);
    console.log('participants:' + participants_list + typeof participants_list)

    formData.append('illustrate', document.getElementById("illustrate").value);
    console.log('illustrate:' + $('#illustrate').val() + typeof $('#illustrate').val())

    formData.append('announce', document.getElementById("announce").checked);
    console.log('announce:' + document.getElementById("announce").checked)

    formData.append('finish', document.getElementById("finish").checked);
    console.log('finish:' + document.getElementById("finish").checked)

    formData.append('roleId', document.getElementById("roleId").value);
    console.log('roleId:' + $('#roleId').val() + typeof $('#roleId').val())


    let id = $('#id').val();
    console.log("id:" + id)
    if (id > 0) {

        formData.append('id', document.getElementById("id").value);
        console.log('id:' + $('#id').val() + typeof $('#id').val())
        console.log(formData)
        $.ajax({
            type: 'POST',
            url: '/fd/work_list/save',
            data: formData,
            dataType: 'json',
            processData: false,
            contentType: false,
            async: true,
            success: function (data) {
                alert('success');
                window.location.reload();
                console.log("success :" + data)
            },
            error: function (ex) {
                alert('error')
                console.log(ex)
                console.log("error :" + ex)
            }
        });
    } else {
        console.log('else')
        alert('else')
        console.log("--------------------------")
        console.log(formData)
        $.ajax({
            type: 'POST',
            url: '/fd/work_list/save',
            data: formData,
            dataType: 'json',
            processData: false,
            contentType: false,
            async: true,
            success: function (data) {
                alert('success');
                window.location.reload();
            },
            error: function (ex) {
                alert('error')
                console.log(ex)
            }
        });
    }
    clear_table();
    window.location.reload();
}

function touch_announce(work_list_id) {
    // alert("touch announce :"+work_list_id)

    $.ajax({
        type: 'POST',
        url: '/fd/work_list/announce/' + work_list_id,
        sync: true,
        success: function (data) {
            // alert(data);
        },
        error: function (e) {
            // alert(e);
        }
    });
    // window.location.reload();
}

function touch_finish(work_list_id) {
    // alert("touch finish!!!!")

    $.ajax({
        type: 'POST',
        url: '/fd/work_list/finish/' + work_list_id,
        sync: true,
        success: function (data) {
            // alert(data);
        },
        error: function (e) {
            // alert(e);
        }
    });
    // window.location.reload();
}

function delete_work_list(work_list_id) {
    $.ajax({
        type: 'DELETE',
        url: '/fd/work_list/delete/' + work_list_id,
        sync: false,
        success: function (data) {
            alert("SUCCESS delete id:" + data)
        },
        error: function (e) {
            alert("ERROR " + e)
        }
    });
    window.location.reload();
}
