/*globals $:false */
/*globals console:false */
/*jshint esversion:6 */
/*jshint evil:true */
/*jshint globalstrict: true */

'use strict';

$('document').ready(function() {
	console.log('doc ready');
});

$('#changeGreeting').click(function() {
	$('#greeting').text('Hello My Friend');
});

$( "#fadein" ).click(function() {
	  $( "#ww" ).fadeIn( "slow", function() {
	    // Animation complete
	  });
});

$( "#fadeout" ).click(function() {
	  $( "#ww" ).fadeOut( "slow", function() {
	    // Animation complete
	  });
});

//map()
let names = [ 'tom', 'tommy', 'thomas'];
names = names.map(x => x.charAt(0).toUpperCase() + x.slice(1));
console.log('names=' + names); // names=Tom,Tommy,Thomas


