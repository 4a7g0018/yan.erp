function show_user_home() {
    $.ajax({
        type: 'GET',
        url: '/fd/home/get_bulletin_board',
        async: false,
        success: function (data) {
            console.log(data);
            let json_data = JSON.parse(JSON.stringify(data));
            let str = '';

            console.log(json_data[0])

            for (let number in json_data) {

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