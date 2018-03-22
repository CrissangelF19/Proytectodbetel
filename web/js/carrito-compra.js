

$(function(){
    $('tr #deleteitem').click(function(e){
        e.preventDefault();
        var elemento = $(this);
        var idproducto = elemento.parent().find('#idarticulo').text();
        $.ajax({
            url : 'BorrarProdCompra',
            type : 'post',
            data : {idproducto : idproducto},
            success: function(r){
                elemento.parent().parent().remove();
                var elementostabla = $('#shop-table tr');
                if(elementostabla.length <= 1){
                    $('#cart-container').append("<h5>No hay Productos en la Compra</h5>");
                }
                $('#txt-subtotal').text(r);                
                $('#txt-total').text(r);
            }           
        })
    });   
  
});

