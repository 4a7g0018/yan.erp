function show_owner_equity_center(){
    $.ajax({
        type: 'GET',
        url: '/gm/account/owner_equity/get_owner_equity',

        success: function (data) {
            let json_data = JSON.parse(JSON.stringify(data));
            let str = '';
            let last_year = '';
            let all_years = [];
            for (let account_id in json_data) {
                console.log(json_data[account_id]);
                let id = json_data[account_id]['id'];
                let accountName = json_data[account_id]['accountName'];
                let date = json_data[account_id]['date'];
                let accountAmount = json_data[account_id]['accountAmount'];
                let remark = json_data[account_id]['remark'];

                let year = date.substr(0, 4);


                if (all_years.indexOf(year) === -1) {
                    all_years.push(year);
                    console.log("push")
                    console.log(all_years)

                    str += '<div class="page-center-top">'
                        + '<div><h2>' + year + '</h2></div>'
                        + '<button id="add_account_button_+' + year + '" class="add_button" onclick="show_account_owner_equity_add_label(' + year + ')">+</button>'
                        + '</div>';

                    str += '<table style="width: 100%;"><thead style="background:#525252; color: white;height: 30px">'
                        + '<th style="width: 16%; min-width: 150px;">Name</th>'
                        + '<th style="width: 12%; min-width: 150px;">time</th>'
                        + '<th style="width: 12%; min-width: 150px;">Amount</th>'
                        + '<th style="width: 35%; min-width: 330px;">Remark</th>'
                        + '<th style="width: 25%; min-width: 220px;">other</th>'
                        + '</thead>';

                    str += '<tr id="add_account_table_' + year + '"></tr>'

                    str += "<tbody id='table_body_" + year + "'></tbody></table>"
                } else {
                    console.log("is find")
                    console.log(all_years)
                }
            }

            $('#account_table').html(str);
            for (let account_id in json_data) {
                console.log(json_data[account_id]);
                let id = json_data[account_id]['id'];
                let accountName = json_data[account_id]['accountName'];
                let date = json_data[account_id]['date'];
                let accountAmount = json_data[account_id]['accountAmount'];
                let remark = json_data[account_id]['remark'];

                let year = date.substr(0, 4);
                let strs = '';
                let format_date = date.substr(0, 10);

                strs += '<td>' + accountName + '</td>'
                    + '<td>' + format_date + '</td>'
                    + '<td>' + accountAmount + '</td>'
                    + '<td>' + remark + '</td>'
                    + '<td>'
                    + '<button id="edit_' + id + '" onclick="edit_account_owner_equity(' + id + ')" class="edit_button">edit</button>'
                    + '<button id="' + id + '" onclick="delete_account_owner_equity(' + id + ')" class="delete_button">delete</button>'
                    + '</td>';

                let strs2 = ''

                let year_div = document.getElementById('table_body_' + year);
                let tr = document.createElement("tr")
                let tr2 = document.createElement("tr")
                tr2.setAttribute("id", "show_edit_owner_equity_box_" + id)

                tr.innerHTML = strs
                tr2.innerHTML = strs2
                year_div.appendChild(tr)
                year_div.appendChild(tr2)
            }


        }
    });
}

function show_account_owner_equity_add_label(year) {

    let str = '';
    str += '<td><input id="account_name_' + year + '" class="input_account_name" type="text"></td>'
        + '<td><input id="time_' + year + '" class="input_account_time" type="date"></td>'
        + '<td><input id="amount_' + year + '" class="input_account_amount" type="number"></td>'
        + '<td><textarea id="remark_' + year + '" class="input_account_remark"></textarea></td>'
        + '<td>'
        + '<button id="clear_' + year + '" class="button_account_clear" onclick="clear_account_owner_equity_label(' + year + ')">clear</button>'
        + '<button id="add_' + year + '" class="button_account_add" onclick="save_account_owner_equity(' + year + ')">add</button>'
        + '<button id="close_' + year + '" class="button_account_close" onclick="close_add_owner_equity_label(' + year + ')">X</button>'
        + '</td>';

    let year_div = document.getElementById('add_account_table_' + year);
    $('#add_account_table_' + year).html(str)
}

function close_add_owner_equity_label(year) {
    let account_name = document.getElementById('account_name_' + year)
    let time = document.getElementById('time_' + year)
    let amount = document.getElementById('amount_' + year)
    let remark = document.getElementById('remark_' + year)
    let add = document.getElementById('add_' + year)
    let clear = document.getElementById('clear_' + year)
    let close = document.getElementById('close_' + year)
    account_name.innerHTML = "";
    time.innerHTML = "";
    amount.innerHTML = "";
    remark.innerHTML = "";
    add.innerHTML = "";
    clear.innerHTML = "";
    close.innerHTML = "";

    let parent_account_name = account_name.parentNode;
    let parent_time = time.parentNode;
    let parent_amount = amount.parentNode;
    let parent_remark = remark.parentNode;
    let parent_add = add.parentNode;
    let parent_clear = clear.parentNode;
    let parent_close = close.parentNode;
    parent_account_name.removeChild(account_name);
    parent_time.removeChild(time);
    parent_amount.removeChild(amount);
    parent_remark.removeChild(remark);
    parent_add.removeChild(add);
    parent_clear.removeChild(clear);
    parent_clear.removeChild(close);
}

function clear_account_owner_equity_label(year) {
    document.getElementById('account_name_' + year).value = null;
    document.getElementById('time_' + year).value = null;
    document.getElementById('amount_' + year).value = null;
    document.getElementById('remark_' + year).value = null;
}

function save_account_owner_equity(year) {
    let form_data = new FormData();

    form_data.append('accountName', $('#account_name_' + year).val());
    form_data.append('time', $('#time_' + year).val());
    form_data.append('amount', $('#amount_' + year).val());
    form_data.append('remark', $('#remark_' + year).val());

    $.ajax({
        type: 'POST',
        url: '/gm/account/owner_equity/save',
        data: form_data,
        dataType: 'json',
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            alert("save success !!")
            console.log(data);
            window.location.reload();
        },
        error: function (e) {
            console.log(e);
        }
    });
    window.location.reload();
}

function edit_account_owner_equity(account_id,is_all=false) {

    let str = '';
    if(is_all){
        str += '<input type="hidden" id="' + account_id + '" value="' + account_id + '">'
            + '<td></td>'
            + '<td><input id="edit_' + account_id + '_account_name" class="edit_input_account_name" type="text"></td>'
            + '<td><input id="edit_' + account_id + '_time" class="edit_input_account_time" type="date"></td>'
            + '<td><input id="edit_' + account_id + '_amount" class="edit_input_account_amount" type="number"></td>'
            + '<td><textarea id="edit_' + account_id + '_remark" class="edit_input_account_remark"></textarea></td>'
            + '<td>'
            + '<button id="edit_' + account_id + '_add" class="edit_button_account_add" onclick="edit_save_owner_equity(' + account_id + ')">save</button>'
            + '<button id="edit_' + account_id + '_clear" class="edit_button_account_clear" onclick="close_edit_owner_equity(' + account_id + ')">x</button>'
            + '</td>';
    }else{
        str += '<input type="hidden" id="' + account_id + '" value="' + account_id + '">'
            + '<td><input id="edit_' + account_id + '_account_name" class="edit_input_account_name" type="text"></td>'
            + '<td><input id="edit_' + account_id + '_time" class="edit_input_account_time" type="date"></td>'
            + '<td><input id="edit_' + account_id + '_amount" class="edit_input_account_amount" type="number"></td>'
            + '<td><textarea id="edit_' + account_id + '_remark" class="edit_input_account_remark"></textarea></td>'
            + '<td>'
            + '<button id="edit_' + account_id + '_add" class="edit_button_account_add" onclick="edit_save_owner_equity(' + account_id + ')">save</button>'
            + '<button id="edit_' + account_id + '_clear" class="edit_button_account_clear" onclick="close_edit_owner_equity(' + account_id + ')">x</button>'
            + '</td>';
    }

    $("#show_edit_owner_equity_box_" + account_id).html(str);

    let edit_box = document.getElementById('show_edit_owner_equity_box_' + account_id);
    edit_box.style.display = "table-row";

    $.ajax({

        type: 'GET',
        url: '/gm/account/owner_equity/edit/' + account_id,
        async: false,
        success: function (data) {
            console.log(data);
            let account_data = JSON.parse(JSON.stringify(data));
            let id = JSON.parse(JSON.stringify(data['id']));
            let date = JSON.parse(JSON.stringify(data['date']));
            let name = JSON.parse(JSON.stringify(data['accountName']));
            let amount = JSON.parse(JSON.stringify(data['accountAmount']));
            let remark = JSON.parse(JSON.stringify(data['remark']));
            let date_format = date.replace(/\//g, '-')

            if (document.getElementById('edit_' + account_id + '_account_name') !== null) {
                document.getElementById('edit_' + account_id + '_account_name').value = name;
                document.getElementById('edit_' + account_id + '_time').value = date_format.substr(0, 10);
                document.getElementById('edit_' + account_id + '_amount').value = amount;
                document.getElementById('edit_' + account_id + '_remark').value = remark;
            }


        },
        error: function (data) {
            alert("error");
            console.log(data);
        }
    });
}

function edit_save_owner_equity(account_id) {
    let form_data = new FormData();

    form_data.append('id', account_id);
    form_data.append('accountName', $('#edit_' + account_id + '_account_name').val());
    form_data.append('time', $('#edit_' + account_id + '_time').val());
    form_data.append('amount', $('#edit_' + account_id + '_amount').val());
    form_data.append('remark', $('#edit_' + account_id + '_remark').val());

    $.ajax({
        type: 'POST',
        url: '/gm/account/owner_equity/edit/save/' + account_id,
        data: form_data,
        dataType: 'json',
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            alert("save success !!")
            console.log(data);
            window.location.reload();
        },
        error: function (e) {
            alert(e)
            console.log(e);
        }
    });
    window.location.reload();
}

function close_edit_owner_equity(account_id) {
    let edit_box = document.getElementById('show_edit_owner_equity_box_' + account_id);
    edit_box.style.display = "none";
    let account_name = document.getElementById('edit_' + account_id + '_account_name')
    let time = document.getElementById('edit_' + account_id + '_time')
    let amount = document.getElementById('edit_' + account_id + '_amount')
    let remark = document.getElementById('edit_' + account_id + '_remark')
    let add = document.getElementById('edit_' + account_id + '_add')
    let clear = document.getElementById('edit_' + account_id + '_clear')
    account_name.innerHTML = "";
    time.innerHTML = "";
    amount.innerHTML = "";
    remark.innerHTML = "";
    add.innerHTML = "";
    clear.innerHTML = "";

    let parent_account_name = account_name.parentNode;
    let parent_time = time.parentNode;
    let parent_amount = amount.parentNode;
    let parent_remark = remark.parentNode;
    let parent_add = add.parentNode;
    let parent_clear = clear.parentNode;
    parent_account_name.removeChild(account_name);
    parent_time.removeChild(time);
    parent_amount.removeChild(amount);
    parent_remark.removeChild(remark);
    parent_add.removeChild(add);
    parent_clear.removeChild(clear);
}

function delete_account_owner_equity(account_id) {
    console.log(account_id);
    if (account_id !== null) {
        $.ajax({
            type: 'DELETE',
            url: '/gm/account/owner_equity/delete/' + account_id,
            async: false,
            success: function (data) {
                alert(data);
            },
            error: function (e) {
                console.log(e);
            }
        });

        window.location.reload();
    }
}