function show_all_center() {
    add_assets_center();
    add_expenses_center();
    add_liabilities_center();
    add_owner_equity_center();
    add_revenue_center();
}

function add_assets_center() {
    $.ajax({
        type: 'GET',
        url: '/gm/account/assets/get_assets',
        async: false,
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
                        + '<button id="add_account_button_+' + year + '" class="add_button" onclick="show_account_assets_add_label(' + year + ')">+</button>'
                        + '</div>';

                    str += '<table style="width: 100%;"><thead style="background:#525252; color: white;height: 30px">'
                        + '<th style="width: 16%; min-width: 150px;">項目</th>'
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


            let old_year = '';
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

                if (old_year !== year) {
                    strs += '<td>資產</td>'
                        + '<td>' + accountName + '</td>'
                        + '<td>' + format_date + '</td>'
                        + '<td>' + accountAmount + '</td>'
                        + '<td>' + remark + '</td>'
                        + '<td>'
                        + '<button id="edit_' + id + '" onclick="edit_account_assets(' + id + ',true)" class="edit_button">edit</button>'
                        + '<button id="' + id + '" onclick="delete_account_assets(' + id + ')" class="delete_button">delete</button>'
                        + '</td>';

                    old_year = year;
                } else {
                    strs += '<td></td>'
                        + '<td>' + accountName + '</td>'
                        + '<td>' + format_date + '</td>'
                        + '<td>' + accountAmount + '</td>'
                        + '<td>' + remark + '</td>'
                        + '<td>'
                        + '<button id="edit_' + id + '" onclick="edit_account_assets(' + id + ',true)" class="edit_button">edit</button>'
                        + '<button id="' + id + '" onclick="delete_account_assets(' + id + ')" class="delete_button">delete</button>'
                        + '</td>';
                }


                let strs2 = ''

                let year_div = document.getElementById('table_body_' + year);
                let tr = document.createElement("tr")
                let tr2 = document.createElement("tr")
                tr2.setAttribute("id", "show_edit_assets_box_" + id)

                tr.innerHTML = strs
                tr2.innerHTML = strs2
                year_div.appendChild(tr)
                year_div.appendChild(tr2)
            }


        }
    });
}

function add_expenses_center() {
    $.ajax({
        type: 'GET',
        url: '/gm/account/expenses/get_expenses',
        async: false,
        success: function (data) {
            let json_data = JSON.parse(JSON.stringify(data));
            let str = '';
            let last_year = '';
            let all_years = [];

            let old_year = '';
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

                if (old_year !== year) {
                    strs += '<td>收益</td>'
                        + '<td>' + accountName + '</td>'
                        + '<td>' + format_date + '</td>'
                        + '<td>' + accountAmount + '</td>'
                        + '<td>' + remark + '</td>'
                        + '<td>'
                        + '<button id="edit_' + id + '" onclick="edit_account_expenses(' + id + ',true)" class="edit_button">edit</button>'
                        + '<button id="' + id + '" onclick="delete_account_expenses(' + id + ')" class="delete_button">delete</button>'
                        + '</td>';

                    old_year = year;
                } else {
                    strs += '<td></td>'
                        + '<td>' + accountName + '</td>'
                        + '<td>' + format_date + '</td>'
                        + '<td>' + accountAmount + '</td>'
                        + '<td>' + remark + '</td>'
                        + '<td>'
                        + '<button id="edit_' + id + '" onclick="edit_account_expenses(' + id + ',true)" class="edit_button">edit</button>'
                        + '<button id="' + id + '" onclick="delete_account_expenses(' + id + ')" class="delete_button">delete</button>'
                        + '</td>';
                }
                let strs2 = ''
                let year_div = document.getElementById('table_body_' + year);
                let tr = document.createElement("tr")
                let tr2 = document.createElement("tr")
                tr2.setAttribute("id", "show_edit_expenses_box_" + id)

                tr.innerHTML = strs
                tr2.innerHTML = strs2
                year_div.appendChild(tr)
                year_div.appendChild(tr2)
            }
        }
    });
}

function add_liabilities_center() {
    $.ajax({
        type: 'GET',
        url: '/gm/account/liabilities/get_liabilities',
        async: false,
        success: function (data) {
            let json_data = JSON.parse(JSON.stringify(data));
            let str = '';
            let last_year = '';
            let all_years = [];

            let old_year = '';
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

                if (old_year !== year) {
                    strs += '<td>負債</td>'
                        + '<td>' + accountName + '</td>'
                        + '<td>' + format_date + '</td>'
                        + '<td>' + accountAmount + '</td>'
                        + '<td>' + remark + '</td>'
                        + '<td>'
                        + '<button id="edit_' + id + '" onclick="edit_account_liabilities(' + id + ',true)" class="edit_button">edit</button>'
                        + '<button id="' + id + '" onclick="delete_account_liabilities(' + id + ')" class="delete_button">delete</button>'
                        + '</td>';

                    old_year = year;
                } else {
                    strs += '<td></td>'
                        + '<td>' + accountName + '</td>'
                        + '<td>' + format_date + '</td>'
                        + '<td>' + accountAmount + '</td>'
                        + '<td>' + remark + '</td>'
                        + '<td>'
                        + '<button id="edit_' + id + '" onclick="edit_account_liabilities(' + id + ',true)" class="edit_button">edit</button>'
                        + '<button id="' + id + '" onclick="delete_account_liabilities(' + id + ')" class="delete_button">delete</button>'
                        + '</td>';
                }

                let strs2 = ''

                let year_div = document.getElementById('table_body_' + year);
                let tr = document.createElement("tr")
                let tr2 = document.createElement("tr")
                tr2.setAttribute("id", "show_edit_liabilities_box_" + id)

                tr.innerHTML = strs
                tr2.innerHTML = strs2
                year_div.appendChild(tr)
                year_div.appendChild(tr2)
            }
        }
    });
}

function add_owner_equity_center() {
    $.ajax({
        type: 'GET',
        url: '/gm/account/owner_equity/get_owner_equity',
        async: false,
        success: function (data) {
            let json_data = JSON.parse(JSON.stringify(data));
            let str = '';
            let last_year = '';
            let all_years = [];

            let old_year = '';
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

                if (old_year !== year) {
                    strs += '<td>權益</td>'
                        + '<td>' + accountName + '</td>'
                        + '<td>' + format_date + '</td>'
                        + '<td>' + accountAmount + '</td>'
                        + '<td>' + remark + '</td>'
                        + '<td>'
                        + '<button id="edit_' + id + '" onclick="edit_account_owner_equity(' + id + ',true)" class="edit_button">edit</button>'
                        + '<button id="' + id + '" onclick="delete_account_owner_equity(' + id + ')" class="delete_button">delete</button>'
                        + '</td>';

                    old_year = year;
                } else {
                    strs += '<td></td>'
                        + '<td>' + accountName + '</td>'
                        + '<td>' + format_date + '</td>'
                        + '<td>' + accountAmount + '</td>'
                        + '<td>' + remark + '</td>'
                        + '<td>'
                        + '<button id="edit_' + id + '" onclick="edit_account_owner_equity(' + id + ',true)" class="edit_button">edit</button>'
                        + '<button id="' + id + '" onclick="delete_account_owner_equity(' + id + ')" class="delete_button">delete</button>'
                        + '</td>';
                }

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

function add_revenue_center() {
    $.ajax({
        type: 'GET',
        url: '/gm/account/revenue/get_revenue',
        async: false,
        success: function (data) {
            let json_data = JSON.parse(JSON.stringify(data));
            let str = '';
            let last_year = '';
            let all_years = [];
            let old_year = '';

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

                if (old_year !== year) {
                    strs += '<td>收益</td>'
                        + '<td>' + accountName + '</td>'
                        + '<td>' + format_date + '</td>'
                        + '<td>' + accountAmount + '</td>'
                        + '<td>' + remark + '</td>'
                        + '<td>'
                        + '<button id="edit_' + id + '" onclick="edit_account_revenue(' + id + ',true)" class="edit_button">edit</button>'
                        + '<button id="' + id + '" onclick="delete_account_revenue(' + id + ')" class="delete_button">delete</button>'
                        + '</td>';

                    old_year = year;
                } else {
                    strs += '<td></td>'
                        + '<td>' + accountName + '</td>'
                        + '<td>' + format_date + '</td>'
                        + '<td>' + accountAmount + '</td>'
                        + '<td>' + remark + '</td>'
                        + '<td>'
                        + '<button id="edit_' + id + '" onclick="edit_account_revenue(' + id + ',true)" class="edit_button">edit</button>'
                        + '<button id="' + id + '" onclick="delete_account_revenue(' + id + ')" class="delete_button">delete</button>'
                        + '</td>';
                }

                let strs2 = ''
                let year_div = document.getElementById('table_body_' + year);
                let tr = document.createElement("tr")
                let tr2 = document.createElement("tr")
                tr2.setAttribute("id", "show_edit_revenue_box_" + id)

                tr.innerHTML = strs
                tr2.innerHTML = strs2
                year_div.appendChild(tr)
                year_div.appendChild(tr2)
            }
        }
    });
}