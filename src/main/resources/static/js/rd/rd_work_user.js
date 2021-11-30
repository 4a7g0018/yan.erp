function show_user_table_thead() {
    let table_thead = '<tr>'
        + '<th style="width: 150px;max-width: 13%">name</th>'
        + '<th style="width: 100px;max-width: 15%">priority</th>'
        + '<th style="width: 150px;max-width: 13%">date</th>'
        + '<th style="width: 300px;max-width: 28%">participants</th>'
        + '<th style="width: 400px;max-width: 30%">illustrate</th>'
        // + '<th style="width: 225px;max-width: 15%"></th>'
        + '</tr>'
    $("#table-thead").html(table_thead);
}

function show_user_table_body(work_list_json) {
    let str;
    if (work_list_json[0]['workName'] !== null) {
        for (let work_list_center in work_list_json) {
            console.log(work_list_json[work_list_center])

            str += '<tr style="cursor: pointer" onclick="show_work_to_number(' + work_list_json[work_list_center]['id'] + ')">'
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

            // str += '<td>'
            //     + '<button onclick="show_work_to_number(' + work_list_json[work_list_center]['id'] + ')" class="show_button_work_list">open</button>'
            //     + '</td>';
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
            // document.getElementById("announce_" + id).checked = work_list_json[json_number]['announce'];
            // document.getElementById("finish_" + id).checked = work_list_json[json_number]['finish'];
        }
    }
}

function show_user_center() {
    $.ajax({
        type: 'GET',
        url: '/rd/work_list/get_announce_work',
        async: true,
        success: function (data) {

            console.log(data)
            work_list_json = JSON.parse(JSON.stringify(data));

            show_user_table_thead()
            // let str;
            document.getElementById("roleId").value = work_list_json[0]["roleId"]
            show_user_table_body(work_list_json)
            // if (work_list_json[0]['workName'] !== null) {
            //     for (let work_list_center in work_list_json) {
            //         console.log(work_list_json[work_list_center])
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
            //
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
            //
            //         str += '<td>'
            //             + '<button onclick="show_work(' + work_list_json[work_list_center]['id'] + ')" class="edit_button_work_list">open</button>'
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

function show_work_to_number(work_list_id) {
    $.ajax({
        type: 'GET',
        url: '/rd/work_list/edit/' + work_list_id,
        sync: true,
        success: function (data) {
            let json = JSON.parse(JSON.stringify(data));
            // console.log(data);
            show_user_work_participants(json['participants']);
            document.getElementById('user_cover_id').value = json['id'];
            document.getElementById('user_job_title').value = json['workName'];
            document.getElementById('user_priority').value = json['priority'];

            let date = json['date'].substr(0, 10)
            document.getElementById('user_date').value = date;
            document.getElementById('user_illustrate').value = json['illustrate'];
            // document.getElementById('announce').checked = json['announce'];
            // document.getElementById('finish').checked = json['finish']

            // document.getElementById('user_roleId').checked = json['roleId']
        }
    });
    // window.location.reload();
}

function show_user_work_center() {
    user_cover.classList.toggle('click');
    user_cover_table_jub_title.classList.toggle('click');
    user_cover_table_priority.classList.toggle('click');
    user_cover_table_date.classList.toggle('click');
    user_cover_table_participants.classList.toggle('click');
    user_cover_table_illustrate.classList.toggle('click');
    user_cover_table_button.classList.toggle('click');
}

function show_user_work_participants(section = []) {

    $.ajax({
        type: 'GET',
        url: '/rd/work_list/get_user',
        async: true,
        success: function (data) {
            let strs = "";
            console.log(data)
            for (let user_name in data) {
                console.log(data[user_name]);
                strs += '<div>'

                strs += '<input id="' + data[user_name] + '" type="checkbox" class="participants participants-quantity" disabled="disabled">'
                    + '<label for="' + data[user_name] + '">' + data[user_name] + '</label>';

                strs += '</div>'
            }
            console.log(strs)

            $('#user_participants_checkbox').html(strs);

            section.forEach(val => {
                document.getElementById(val).checked = true
            })

            show_user_work_center();
        }
    });

};

function close_table(){
    show_user_work_center();
}