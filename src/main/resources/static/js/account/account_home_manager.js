function show_manager_home() {
    let add_bulletin_board_btn = '<input type="button" value="新增公告" onclick="add_bulletin_board()">';
    $('#add_bulletin_board_btn').html(add_bulletin_board_btn);

    $.ajax({
        type: 'GET',
        url: '/account/home/get_bulletin_board',
        async: false,
        success: function (data) {
            console.log(data);
            let json_data = JSON.parse(JSON.stringify(data));
            let str = '';

            console.log(json_data[0])

            for (number in json_data) {

                str += '<table>'
                if (json_data[number]['departmentId'] === 1) {
                    str += '<thead class="thead_' + json_data[number]['id'] + '" onClick="show_bulletin_board(' + json_data[number]['id'] + ')">'
                } else {
                    str += '<thead  class="thead_' + json_data[number]['id'] + '" onClick="show_bulletin_board(' + json_data[number]['id'] + ')">'
                }

                str += '<th style="width: 33%;">' + json_data[number]['title'] + '</th>'
                    + '<th style="width: 33%;">' + json_data[number]['sponsor'] + '</th>'
                    + '<th style="width: 33%;">' + json_data[number]['date'] + '</th>'
                    + '</thead>'
                    + '</table>'
                    + '<div class="bulletin_board_center_' + json_data[number]['id'] + ' bulletin_board_center">'
                    + '<div><label>標　題 :</label><div style="display: inline-block">' + json_data[number]['title'] + '</div></div>'
                    + '<div><label>發布人 :</label><div style="display: inline-block">' + json_data[number]['sponsor'] + '</div></div>'
                    + '<div><label>發布日 :</label><div style="display: inline-block">' + json_data[number]['date'] + '</div></div>'
                    + '<div><label>內　容 :</label><div style="display: inline-block;max-width: 99%">' + json_data[number]['content'] + '</div></div>'
                    + '</div>'
            }
            $('#bulletin_board').html(str);
        },
        error: function (e) {
            console.log(e);
        }
    });
}

function show_bulletin_board(id) {
    let center = document.querySelector('.bulletin_board_center_' + id);
    let thead = document.querySelector('.thead_' + id);

    if (center.style.display !== 'block') {
        center.style.display = 'block';
        thead.style.backgroundColor = '#999';

    } else {
        center.style.display = 'none';
        thead.style.backgroundColor = 'white';
    }
}

function add_bulletin_board(priority) {
    cover.classList.toggle('click');
    cover_table_jub_title.classList.toggle('click');
    cover_table_priority.classList.toggle('click');
    cover_table_date.classList.toggle('click');
    cover_table_illustrate.classList.toggle('click');
    cover_table_button.classList.toggle('click');

    $.ajax({
        type:'GET',
        url:'/account/home/get_name',
        async: false,
        success:function (data){
            document.getElementById("priority").value = data;
        },
        error:function (e){
            console.log(e);
        }
    });

}

function upload_work_list() {
    let formData = new FormData();

    formData.append('title', document.getElementById("job_title").value);

    formData.append('date', document.getElementById("date").value);

    formData.append('sponsor', document.getElementById("priority").value);

    formData.append('content', document.getElementById("illustrate").value);

    // formData.append('departmentId', document.getElementById("roleId").value);

    let id = $('#id').val();
    console.log("id:" + id)

    $.ajax({
        type: 'POST',
        url: '/account/home/save_bulletin_board',
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

    clear_add_bulletin_board();
    window.location.reload();
}

function clear_add_bulletin_board() {
    document.getElementById("id").value = null;
    document.getElementById("job_title").value = null;
    document.getElementById("priority").value = null;
    document.getElementById("date").value = null;
    document.getElementById("illustrate").value = null;
    add_bulletin_board();
}