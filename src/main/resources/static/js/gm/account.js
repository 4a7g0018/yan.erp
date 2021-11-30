function show_assets() {
    $.ajax({
        type: 'GET',
        url: '/gm/account/get_assets',
        async: true,
        success: function (assets_data) {
            console.log(assets_data);
            let json_assets =JSON.parse(JSON.stringify(assets_data));
            let str='';
            for (let assets_id in json_assets){
                let assets_date = json_assets[assets_id]['date'].substr(0,10);
                let assets_name = json_assets[assets_id]['accountName'];
                let account_amount = json_assets[assets_id]['accountAmount'];
                str+='<tr>'
                    +'<td>'+assets_name+'</td>'
                    +'<td>'+assets_date+'</td>'
                    +'<td>'+account_amount+'</td>'
                    +'</tr>'
            }
            $('#tbody-assets').html(str)
        },
        error: function (error) {
            console.log(error)
        }

    });
}


function show_liabilities() {
    $.ajax({
        type: 'GET',
        url: '/gm/account/get_liabilities',
        async: true,
        success: function (liabilities_data) {
            let json_liabilities =JSON.parse(JSON.stringify(liabilities_data));
            let str='';
            for (let liabilities_id in json_liabilities){
                let liabilities_date = json_liabilities[liabilities_id]['date'].substr(0,10);
                let liabilities_name = json_liabilities[liabilities_id]['accountName'];
                let liabilities_amount = json_liabilities[liabilities_id]['accountAmount'];
                str+='<tr>'
                    +'<td>'+liabilities_name+'</td>'
                    +'<td>'+liabilities_date+'</td>'
                    +'<td>'+liabilities_amount+'</td>'
                    +'</tr>'
            }
            $('#tbody-liabilities').html(str)
        },
        error: function (error) {
            console.log(error)
        }

    })
}

function show_owner_equity() {
    $.ajax({
        type: 'GET',
        url: '/gm/account/get_owner_equity',
        async: true,
        success: function (owner_equity_data) {
            let json_owner_equity =JSON.parse(JSON.stringify(owner_equity_data));
            let str='';
            for (let owner_equity_id in json_owner_equity){
                let owner_equity_date = json_owner_equity[owner_equity_id]['date'].substr(0,10);
                let owner_equity_name = json_owner_equity[owner_equity_id]['accountName'];
                let owner_equity_amount = json_owner_equity[owner_equity_id]['accountAmount'];
                str+='<tr>'
                    +'<td>'+owner_equity_name+'</td>'
                    +'<td>'+owner_equity_date+'</td>'
                    +'<td>'+owner_equity_amount+'</td>'
                    +'</tr>'
            }
            $('#tbody-owners_equity').html(str)
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function show_revenue() {
    $.ajax({
        type: 'GET',
        url: '/gm/account/get_revenue',
        async: true,
        success: function (revenue_data) {
            let json_revenue_data =JSON.parse(JSON.stringify(revenue_data));
            let str='';
            for (let revenue_id in json_revenue_data){
                let revenue_date = json_revenue_data[revenue_id]['date'].substr(0,10);
                let revenue_name = json_revenue_data[revenue_id]['accountName'];
                let revenue_amount = json_revenue_data[revenue_id]['accountAmount'];
                str+='<tr>'
                    +'<td>'+revenue_name+'</td>'
                    +'<td>'+revenue_date+'</td>'
                    +'<td>'+revenue_amount+'</td>'
                    +'</tr>'
            }
            $('#tbody-revenue').html(str)
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function show_expenses() {
    $.ajax({
        type: 'GET',
        url: '/gm/account/get_expenses',
        async: true,
        success: function (expenses_data) {
            console.log(expenses_data);
            let json_expenses =JSON.parse(JSON.stringify(expenses_data));
            let str='';
            for (let expenses_id in json_expenses){
                let expenses_date = json_expenses[expenses_id]['date'].substr(0,10);
                let expenses_name = json_expenses[expenses_id]['accountName'];
                let expenses_amount = json_expenses[expenses_id]['accountAmount'];
                str+='<tr>'
                    +'<td>'+expenses_name+'</td>'
                    +'<td>'+expenses_date+'</td>'
                    +'<td>'+expenses_amount+'</td>'
                    +'</tr>'
            }
            $('#tbody-expenses').html(str)
        },
        error: function (error) {
            console.log(error)
        }

    });
}