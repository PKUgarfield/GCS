
$(document).ready(function(){

	var login = $('#loginform');
	var recover = $('#recoverform');
	var register = $('#registerform');
	var speed = 400;
	
	recover.hide();
	register.hide();

	$('#to-recover').click(function(){
		
		$("#loginform").slideUp();
		$("#registerform").hide();
		$("#recoverform").fadeIn();
	});
	$('#to-login').click(function(){
		
		$("#recoverform").hide();
		$("#registerform").hide();
		$("#loginform").fadeIn();
	});
	$('#to-register').click(function(){
		
		$("#recoverform").hide();
		$("#loginform").hide();
		$("#registerform").fadeIn();
	});
	
	
	$('#to-login2').click(function(){
		$("#recoverform").hide();
		$("#registerform").hide();
		$("#loginform").fadeIn();
	});
    
    if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
       
        var input = $(this);       
       
        $(input).val(input.attr('placeholder'));
               
        $(input).focus(function(){
             if (input.val() == input.attr('placeholder')) {
                 input.val('');
             }
        });
       
        $(input).blur(function(){
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.val(input.attr('placeholder'));
            }
        });
    });

        
        
    }
});