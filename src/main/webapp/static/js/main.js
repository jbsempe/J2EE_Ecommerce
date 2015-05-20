$(document).ready(function(){
	$('.add-quantite').click(function(e){
		e.preventDefault();
		number = parseInt($(this).parent().parent().find('.number').text());
		$(this).parent().parent().find('.number').text(number + 1);
		$(this).parent().parent().parent().parent().find('.nbre').val(number + 1);
	});
	
	$('.remove-quantite').click(function(e){
		e.preventDefault();
		number = parseInt($(this).parent().parent().find('.number').text());
		if(number > 1){
			$(this).parent().parent().find('.number').text(number - 1);
			$(this).parent().parent().parent().parent().find('.nbre').val(number - 1);
		}
	});
	
	
	var totalCart = 0;
	$('.total-price').each(function(){
		price = parseFloat($(this).parent().parent().find('.price').text());
		number = parseInt($(this).parent().parent().find('.number').text());
		
		$(this).text(calcTotalPrice(number, price));
		
		totalCart = totalCart + calcTotalPrice(number, price);
	});
	
	$('.total-cart').text(totalCart);
	
	
	function calcTotalPrice(number, price){
		totalPrice = number * price;
		
		return totalPrice;
	}
});