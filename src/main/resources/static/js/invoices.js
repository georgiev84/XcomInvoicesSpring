
const URLS = {
    invoices: '/api/invoices',
}

const toString = ({invoiceAcct, invoiceSum, documentAcct, invoiceDate}) => `
            <tr>
                <td></td>
                <td><a href="/invoices/${invoiceAcct}">${invoiceAcct}</a></td>
                <td>${invoiceDate}</td>
                <td>${invoiceSum}</td>
                <td><a target="_blank" href="/pdfreport/${invoiceAcct}"><img src="/images/pdf.png" th:width="25px" th:height="25px" th:title="Свали"/></a></td>
            </tr>
`


// fetch(URLS.invoices)
//     .then(response => response.json())
// .then( data => {
//     console.log("data", data);
// });

fetch(URLS.invoices)
    .then(response => response.json())
.then(invoices => {
    let result = '';
    invoices.forEach(invoice => {
        const invoiceString = toString(invoice);
        result += invoiceString;
    });
    console.log(result);
    document.getElementById('invoice-table').innerHTML = result;
});
