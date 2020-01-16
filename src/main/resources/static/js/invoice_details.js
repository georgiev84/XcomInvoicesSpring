
const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },

}
const toString = ({name, price, qtty, code}) => ` 
            <tr>
                <td >${code}</td>
                <td class="text-left">${name}</td>
                <td>${qtty}</td>
                <td>${price.toFixed(4)}</td>
                <td>${(price * qtty).toFixed(2)}</td>

            </tr>
`;


const URLS = {
    invoiceDetails: '/api/invoices'
};

var pathArray = window.location.pathname.split('/');

loader.show();
fetch(URLS.invoiceDetails + "/"+pathArray[2])
    .then(response => response.json())
    .then(docs => {

        var productList = docs.productsInInvoice;
        var invoiceInfo = docs.invoiceBasicInfo;

        $('#invoice-info').text("Фактура "+ invoiceInfo.invoiceAcct +" от "+ formatDate(invoiceInfo.invoiceDate));
        $('#invoice-info-sum').text("Обща стойност "+ invoiceInfo.invoiceSum.toFixed(2) + " лв.");
        $('#invoice-pdf-link').attr("href", "/pdfreport/"+invoiceInfo.invoiceAcct);


        let result = '';
        productList.forEach( product => {
            const invoiceString = toString(product);
            result += invoiceString;
        });
        $('#invoice-details-table').html(result);
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
// loader.show();
// fetch(URLS.invoiceDetails + "/"+pathArray[2])
//
//     .then(response => response.json())
//     .then(invoices => {
//         let result = '';
//         invoices.forEach(invoice => {
//             const invoiceString = toString(invoice);
//             result += invoiceString;
//         });
//
//         // document.getElementById('invoice-table').innerHTML = result;
//         $('#invoice-details-table').html(result);
//         loader.hide();
//     });