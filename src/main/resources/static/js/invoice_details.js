
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
`



const URLS = {
    invoiceDetails: '/api/invoices'
}

var pathArray = window.location.pathname.split('/');

 loader.show();
fetch(URLS.invoiceDetails + "/"+pathArray[2])

    .then(response => response.json())
    .then(invoices => {
        let result = '';
        invoices.forEach(invoice => {
            const invoiceString = toString(invoice);
            result += invoiceString;
        });

        // document.getElementById('invoice-table').innerHTML = result;
        $('#invoice-details-table').html(result);
         loader.hide();
    });