const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },

}

const URLS = {
    invoices: '/api/invoices',
    invoiceDetails: '/api/invoices/{id}'
}

const toString = ({invoiceAcct, invoiceSum, documentAcct, invoiceDate}) => `
            <tr>
                <td><a href="/invoices/${invoiceAcct}">Отвори</a></td>
                <td>${invoiceAcct}</td>
                <td>${formatDate(invoiceDate)}</td>
                <td>${invoiceSum.toFixed(2)} лв.</td>
                <td><a target="_blank" href="/pdfreport/${invoiceAcct}"><img src="/images/pdf.png" th:width="25px" th:height="25px" th:title="Свали"/></a></td>
            </tr>
`


// fetch(URLS.invoices)
//     .then(response => response.json())
// .then( data => {
//     console.log("data", data);
// });

loader.show();
fetch(URLS.invoices)

    .then(response => response.json())
.then(invoices => {
    let result = '';
    invoices.forEach(invoice => {
        const invoiceString = toString(invoice);
        result += invoiceString;
    });

    // document.getElementById('invoice-table').innerHTML = result;
    $('#invoice-table').html(result);
    loader.hide();
});



function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [day, month, year].join('-');
}